/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.source.formatter.check;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.source.formatter.util.SourceFormatterUtil;

import java.io.IOException;

/**
 * @author Kevin Lee
 */
public class XMLUpgradeDTDVersionCheck extends XMLDTDVersionCheck {

	@Override
	protected String doProcess(
			String fileName, String absolutePath, String content)
		throws IOException {

		_upgradeToVersion = getAttributeValue(
			SourceFormatterUtil.UPGRADE_TO_LIFERAY_VERSION, absolutePath);

		if ((_upgradeToVersion == null) || !fileName.endsWith(".xml")) {
			return content;
		}

		return checkDTDVersion(content);
	}

	@Override
	protected String getLPVersion() {
		return _getUpgradeToVersion(".");
	}

	@Override
	protected String getLPVersionDTD() {
		return _getUpgradeToVersion("_");
	}

	private String _getUpgradeToVersion(String separator) {
		String[] upgradeToVersionParts = StringUtil.split(
			_upgradeToVersion, StringPool.PERIOD);

		if ((upgradeToVersionParts == null) ||
			(upgradeToVersionParts.length < 2)) {

			return null;
		}

		if (upgradeToVersionParts[0].length() == 4) {
			return StringBundler.concat("7", separator, "4", separator, "0");
		}

		return StringBundler.concat(
			upgradeToVersionParts[0], separator, upgradeToVersionParts[1],
			separator, "0");
	}

	private String _upgradeToVersion;

}