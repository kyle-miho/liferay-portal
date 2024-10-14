/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.source.formatter.check;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.source.formatter.check.util.JavaSourceUtil;
import com.liferay.source.formatter.parser.JavaClass;
import com.liferay.source.formatter.parser.JavaClassParser;
import com.liferay.source.formatter.parser.JavaMethod;
import com.liferay.source.formatter.parser.JavaParameter;
import com.liferay.source.formatter.parser.JavaSignature;
import com.liferay.source.formatter.parser.JavaTerm;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kyle Miho
 * @author Michael Cavalcanti
 */
public class UpgradeJavaFDSDataProviderCheck extends BaseUpgradeCheck {

	@Override
	protected String format(
			String fileName, String absolutePath, String content)
		throws Exception {

		JavaClass javaClass = JavaClassParser.parseJavaClass(fileName, content);

		List<String> implementedClassNames =
			javaClass.getImplementedClassNames();

		if (!implementedClassNames.contains("ClayDataSetDataProvider") &&
			!implementedClassNames.contains("CommerceDataSetDataProvider")) {

			return content;
		}

		content = _updateProviderKey(content);

		content = _updateImplementedClass(content);

		content = _updateServiceClass(content);

		for (JavaTerm childJavaTerm : javaClass.getChildJavaTerms()) {
			if (!childJavaTerm.isJavaMethod()) {
				continue;
			}

			JavaMethod javaMethod = (JavaMethod)childJavaTerm;

			String javaMethodContent = javaMethod.getContent();

			String newJavaMethodContent = javaMethodContent;

			if (_hasUpgradeableMethod(
					javaMethod, _originalGetItemsParameterList,
					Arrays.asList("getItems"), "List")) {

				for (int i = 0; i < _originalGetItemsParameterList.size();
					 ++i) {

					newJavaMethodContent = StringUtil.replace(
						newJavaMethodContent,
						_originalGetItemsParameterList.get(i),
						_upgradedGetItemsParameterList.get(i));
				}

				newJavaMethodContent = _reorderGetItems(newJavaMethodContent);
			}

			if (_hasUpgradeableMethod(
					javaMethod, _originalCountItemsParameterList,
					Arrays.asList("countItems", "getItemsCount"), "int")) {

				newJavaMethodContent = StringUtil.replace(
					newJavaMethodContent, "countItems", "getItemsCount");

				for (int i = 0; i < _originalCountItemsParameterList.size();
					 ++i) {

					newJavaMethodContent = StringUtil.replace(
						newJavaMethodContent,
						_originalCountItemsParameterList.get(i),
						_upgradedCountItemsParameterList.get(i));
				}

				newJavaMethodContent = _reorderCountItems(newJavaMethodContent);
			}

			newJavaMethodContent = _checkMethodCalls(
				content, fileName, newJavaMethodContent);

			content = StringUtil.replace(
				content, javaMethodContent, newJavaMethodContent);
		}

		return content;
	}

	@Override
	protected String[] getNewImports() {
		return new String[] {
			"com.liferay.frontend.data.set.provider.FDSDataProvider",
			"com.liferay.frontend.data.set.provider.search.FDSKeywords",
			"com.liferay.frontend.data.set.provider.search.FDSPagination"
		};
	}

	private boolean _checkMethodCall(
		String content, String fileName, String javaMethodContent,
		String methodCall) {

		List<String> parameterList = JavaSourceUtil.getParameterList(
			methodCall);

		String variableTypeName = getVariableTypeName(
			javaMethodContent, null, content, fileName, parameterList.get(0));

		if (variableTypeName == null) {
			return false;
		}

		if (variableTypeName.equals("HttpServletRequest") &&
			hasClassOrVariableName(
				"FDSDataProvider", javaMethodContent, content, fileName,
				methodCall)) {

			return true;
		}

		return false;
	}

	private String _checkMethodCalls(
			String content, String fileName, String javaMethodContent)
		throws Exception {

		Matcher methodCallGetItemsMatcher = _methodCallGetItemsPattern.matcher(
			javaMethodContent);

		while (methodCallGetItemsMatcher.find()) {
			String methodCall = JavaSourceUtil.getMethodCall(
				javaMethodContent, methodCallGetItemsMatcher.start());

			if (_checkMethodCall(
					content, fileName, javaMethodContent, methodCall)) {

				javaMethodContent = StringUtil.replace(
					javaMethodContent, methodCall,
					_reorderGetItems(methodCall));
			}
		}

		Matcher methodCallGetItemsCountMatcher =
			_methodCallGetItemsCountPattern.matcher(javaMethodContent);

		while (methodCallGetItemsCountMatcher.find()) {
			String methodCall = JavaSourceUtil.getMethodCall(
				javaMethodContent, methodCallGetItemsCountMatcher.start());

			if (_checkMethodCall(
					content, fileName, javaMethodContent, methodCall)) {

				javaMethodContent = StringUtil.replace(
					javaMethodContent, methodCall,
					_reorderCountItems(methodCall));
			}
		}

		return javaMethodContent;
	}

	private boolean _hasUpgradeableMethod(
		JavaMethod javaMethod, List<String> methodParameters,
		List<String> methodNames, String returnType) {

		if (!methodNames.contains(javaMethod.getName())) {
			return false;
		}

		JavaSignature javaSignature = javaMethod.getSignature();

		if (!Objects.equals(javaSignature.getReturnType(), returnType)) {
			return false;
		}

		List<JavaParameter> javaParameters = javaSignature.getParameters();

		if (methodParameters.size() != javaParameters.size()) {
			return false;
		}

		for (int i = 0; i < methodParameters.size(); ++i) {
			if (!Objects.equals(
					methodParameters.get(i),
					javaParameters.get(
						i
					).getParameterType())) {

				return false;
			}
		}

		return true;
	}

	private String _reorderCountItems(String methodCall) {
		List<String> parameterList = JavaSourceUtil.getParameterList(
			methodCall);

		return StringUtil.replace(
			methodCall, JavaSourceUtil.getParameters(methodCall),
			StringBundler.concat(
				parameterList.get(1), StringPool.COMMA_AND_SPACE,
				parameterList.get(0)));
	}

	private String _reorderGetItems(String methodCall) {
		List<String> parameterList = JavaSourceUtil.getParameterList(
			methodCall);

		return StringUtil.replace(
			methodCall, JavaSourceUtil.getParameters(methodCall),
			StringBundler.concat(
				parameterList.get(1), StringPool.COMMA_AND_SPACE,
				parameterList.get(2), StringPool.COMMA_AND_SPACE,
				parameterList.get(0), StringPool.COMMA_AND_SPACE,
				parameterList.get(3)));
	}

	private String _updateImplementedClass(String content) {
		Matcher matcher = _implementedClassPattern.matcher(content);

		if (matcher.find()) {
			return StringUtil.replace(
				content, matcher.group(), matcher.group(1) + "FDSDataProvider");
		}

		return content;
	}

	private String _updateProviderKey(String content) {
		if (content.contains("clay.data.provider.key")) {
			return StringUtil.replace(
				content, "clay.data.provider.key", "fds.data.provider.key");
		}
		else if (content.contains("commerce.data.provider.key")) {
			return StringUtil.replace(
				content, "commerce.data.provider.key", "fds.data.provider.key");
		}

		return content;
	}

	private String _updateServiceClass(String content) {
		Matcher matcher = _serviceClassPattern.matcher(content);

		if (matcher.find()) {
			return StringUtil.replace(
				content, matcher.group(),
				matcher.group(1) + "FDSDataProvider.class");
		}

		return content;
	}

	private static final Pattern _implementedClassPattern = Pattern.compile(
		"(implements\\s*)" +
			"(ClayDataSetDataProvider|CommerceDataSetDataProvider)");
	private static final Pattern _methodCallGetItemsCountPattern =
		Pattern.compile("\\w+\\.getItemsCount\\s*\\(\\s*.+,\\s*.+\\s*\\)");
	private static final Pattern _methodCallGetItemsPattern = Pattern.compile(
		"\\w+\\.getItems\\s*\\(\\s*.+,\\s*.+,\\s*.+,\\s*.+\\s*\\)");
	private static final List<String> _originalCountItemsParameterList =
		Arrays.asList("HttpServletRequest", "Filter");
	private static final List<String> _originalGetItemsParameterList =
		Arrays.asList("HttpServletRequest", "Filter", "Pagination", "Sort");
	private static final Pattern _serviceClassPattern = Pattern.compile(
		"(service\\s*=\\s*)(ClayDataSetDataProvider|" +
			"CommerceDataSetDataProvider)\\.class");
	private static final List<String> _upgradedCountItemsParameterList =
		Arrays.asList("HttpServletRequest", "FDSKeywords");
	private static final List<String> _upgradedGetItemsParameterList =
		Arrays.asList(
			"HttpServletRequest", "FDSKeywords", "FDSPagination", "Sort");

}