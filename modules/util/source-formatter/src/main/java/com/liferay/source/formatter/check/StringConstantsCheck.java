/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.source.formatter.check;

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.MathUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kyle Miho
 */
public class StringConstantsCheck extends BaseFileCheck {

	@Override
	public boolean isLiferaySourceCheck() {
		return true;
	}

	@Override
	protected String doProcess(
			String fileName, String absolutePath, String content)
		throws Exception {

		if (!fileName.endsWith(".java")) {
			return content;
		}

		content = _fixStringBundlerAppends(content);

		content = _fixAddition(content);

		content = _fixStringBundlerConcat(content);

		return content;
	}

	private int _countUnescapedQuotes(String substring) {
		int count = 0;

		for (int i = 0; i < substring.length(); ++i) {
			char c = substring.charAt(i);

			if ((c == '"') && (i == 0)) {
				++count;
			}

			if ((c == '"') && (i > 0) && (substring.charAt(i - 1) != '\\')) {
				++count;
			}
		}

		return count;
	}

	private String _fixAddition(String content) throws Exception {
		Matcher matcher = _additionPattern.matcher(content);

		while (matcher.find()) {
			if (_isConstant(matcher.group(2))) {
				content = StringUtil.replace(
					content, matcher.group(),
					StringBundler.concat(
						CharPool.QUOTE, matcher.group(1),
						_getConstant(matcher.group(2)), CharPool.QUOTE));
			}
		}

		matcher = _alternateAdditionPattern.matcher(content);

		while (matcher.find()) {
			if (_isConstant(matcher.group(1))) {
				content = StringUtil.replace(
					content, matcher.group(),
					StringBundler.concat(
						CharPool.QUOTE, _getConstant(matcher.group(1)),
						matcher.group(2), CharPool.QUOTE));
			}
		}

		return content;
	}

	private String _fixStringBundlerAppends(String content) throws Exception {
		List<String> stringBundlerNames = _getStringBundlerNames(content);

		for (String stringBundlerName : stringBundlerNames) {
			Pattern pattern = Pattern.compile(
				StringBundler.concat(
					stringBundlerName, "\\.append\\(\\\"(.*?)\\\"\\);\\s*",
					stringBundlerName, "\\.append\\((\\w+\\.[A-Z0-9_]+)\\);"));

			Matcher matcher = pattern.matcher(content);

			while (matcher.find()) {
				if (_isConstant(matcher.group(2))) {
					content = StringUtil.replace(
						content, matcher.group(),
						StringBundler.concat(
							stringBundlerName, ".append(\"", matcher.group(1),
							_getConstant(matcher.group(2)), "\");"));
				}
			}

			pattern = Pattern.compile(
				StringBundler.concat(
					stringBundlerName,
					"\\.append\\s*\\((\\w+\\.[A-Z0-9_]+)\\);\\s*",
					stringBundlerName, "\\.append\\(\\\"(.*?)\\\"\\);"));

			matcher = pattern.matcher(content);

			while (matcher.find()) {
				if (_isConstant(matcher.group(1))) {
					content = StringUtil.replace(
						content, matcher.group(),
						StringBundler.concat(
							stringBundlerName, ".append(\"",
							_getConstant(matcher.group(1)), matcher.group(2),
							"\");"));
				}
			}
		}

		return content;
	}

	private String _fixStringBundlerConcat(String content) throws Exception {
		int sbIndex = content.indexOf("StringBundler.concat(");

		while (sbIndex != -1) {
			int index = sbIndex;

			if (index == -1) {
				return content;
			}

			index = index + "StringBundler.concat(".length();

			int endingIndex = _getEndingIndex(content, index);

			int nextIndex = _getNextCommaIndex(content, index + 1, endingIndex);

			String substring = content.substring(index, nextIndex);

			index = nextIndex;

			while (index < endingIndex) {
				nextIndex = _getNextCommaIndex(content, index + 1, endingIndex);

				String nextSubstring = content.substring(index + 1, nextIndex);

				index = nextIndex;

				if (_isConstant(substring.trim()) &&
					nextSubstring.trim(
					).startsWith(
						"\""
					)) {

					content = StringUtil.replace(
						content, substring + "," + nextSubstring,
						StringBundler.concat(
							"\"", _getConstant(substring.trim()),
							_removeOuterQuotesAndWhitespace(nextSubstring),
							"\""));

					endingIndex = content.indexOf(");", index);
				}

				if (_isConstant(nextSubstring.trim()) &&
					substring.trim(
					).startsWith(
						"\""
					)) {

					content = StringUtil.replace(
						content, substring + "," + nextSubstring,
						StringBundler.concat(
							"\"", _removeOuterQuotesAndWhitespace(substring),
							_getConstant(nextSubstring.trim()), "\""));

					endingIndex = content.indexOf(");", index);
				}

				substring = nextSubstring;
			}

			sbIndex = content.indexOf("StringBundler.concat(", sbIndex + 1);
		}

		return content;
	}

	private String _getConstant(String key) throws Exception {
		String constant = StringPool.BLANK;

		Matcher matcher = _constantPattern.matcher(key);

		if (!matcher.matches()) {
			return constant;
		}

		if (Objects.equals(matcher.group(1), "CharPool")) {
			Field field = ReflectionUtil.getDeclaredField(
				CharPool.class, matcher.group(2));

			constant = field.get(
				null
			).toString();
		}

		if (Objects.equals(matcher.group(1), "StringPool")) {
			Field field = ReflectionUtil.getDeclaredField(
				StringPool.class, matcher.group(2));

			constant = field.get(
				null
			).toString();
		}

		if (Objects.equals(constant, StringPool.NEW_LINE)) {
			return "\\n";
		}

		return constant;
	}

	private int _getEndingIndex(String content, int startingIndex) {
		int level = 1;

		int index = startingIndex;

		while (level != 0) {
			int openIndex = content.indexOf("(", index);

			int closeIndex = content.indexOf(")", index);

			int nextIndex = _getNextParenthesisIndex(closeIndex, openIndex);

			String substring = content.substring(index, nextIndex);

			level = getLevel(substring, "(", ")");

			index = nextIndex;
		}

		return index;
	}

	private int _getNextCommaIndex(String content, int index, int endingIndex) {
		String substring = "";

		while (true) {
			int nextIndex = content.indexOf(",", index + 1);

			if ((nextIndex == -1) || (nextIndex > endingIndex)) {
				return endingIndex;
			}

			substring = substring + content.substring(index, nextIndex);

			if ((getLevel(substring) == 0) &&
				MathUtil.isEven(_countUnescapedQuotes(substring))) {

				return nextIndex;
			}

			index = nextIndex;
		}
	}

	private int _getNextParenthesisIndex(
		int closingParenIndex, int openParenIndex) {

		if (closingParenIndex == -1) {
			return openParenIndex;
		}

		if (openParenIndex == -1) {
			return closingParenIndex;
		}

		return Math.min(closingParenIndex, openParenIndex);
	}

	private List<String> _getStringBundlerNames(String content) {
		List<String> stringBundlerNames = new ArrayList<>();

		Matcher matcher = _stringBundlerPattern.matcher(content);

		while (matcher.find()) {
			stringBundlerNames.add(matcher.group(1));
		}

		return stringBundlerNames;
	}

	private boolean _isConstant(String constant) throws Exception {
		if (Objects.equals(_getConstant(constant), StringPool.BLANK)) {
			return false;
		}

		return true;
	}

	private String _removeOuterQuotesAndWhitespace(String substring) {
		String string = substring.trim();

		int index = string.indexOf("\"");

		int lastIndex = string.lastIndexOf("\"");

		return string.substring(index + 1, lastIndex);
	}

	private static final Pattern _additionPattern = Pattern.compile(
		"\\\"(.*?)\\\"\\s*\\+\\s*(\\w+\\.[A-Z0-9_]+)");
	private static final Pattern _alternateAdditionPattern = Pattern.compile(
		"(\\w+\\.[A-Z0-9_]+)\\s*\\+\\s*\\\"(.*?)\\\"");
	private static final Pattern _constantPattern = Pattern.compile(
		"(CharPool|StringPool)\\.([A-Z0-9_]+)");
	private static final Pattern _stringBundlerPattern = Pattern.compile(
		"StringBundler\\s*(\\w+)\\s*=\\s*new\\s*StringBundler\\(");

}