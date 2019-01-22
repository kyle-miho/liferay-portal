/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.asset.display.page.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.display.page.model.AssetDisplayPageEntry;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.PortalUtil;

/**
 * @author Pavel Savinov
 */
@ProviderType
public class AssetDisplayPageEntryImpl extends AssetDisplayPageEntryBaseImpl {

	public AssetDisplayPageEntryImpl() {
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(AssetDisplayPageEntry.class.getName()),
			StagedModelType.REFERRER_CLASS_NAME_ID_ANY);
	}

}