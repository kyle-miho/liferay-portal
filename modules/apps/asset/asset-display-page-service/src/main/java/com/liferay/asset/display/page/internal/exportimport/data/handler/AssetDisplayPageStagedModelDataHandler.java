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

package com.liferay.asset.display.page.internal.exportimport.data.handler;

import com.liferay.asset.display.page.model.AssetDisplayPageEntry;
import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.exportimport.data.handler.base.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.xml.Element;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(immediate = true, service = StagedModelDataHandler.class)
public class AssetDisplayPageStagedModelDataHandler
	extends BaseStagedModelDataHandler<AssetDisplayPageEntry> {

	public static final String[] CLASS_NAMES = {
		AssetDisplayPageEntry.class.getName()
	};

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext,
			AssetDisplayPageEntry assetDisplayPageEntry)
		throws Exception {

		Element assetDisplayPageElement =
			portletDataContext.getExportDataElement(assetDisplayPageEntry);

		assetDisplayPageElement.addAttribute(
			"class-name", assetDisplayPageEntry.getClassName());

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				assetDisplayPageEntry.getClassName());

		AssetRenderer assetRenderer = assetRendererFactory.getAssetRenderer(
			assetDisplayPageEntry.getClassPK());

		StagedModelDataHandlerUtil.exportReferenceStagedModel(
			portletDataContext, assetDisplayPageEntry,
			(StagedModel)assetRenderer.getAssetObject(),
			PortletDataContext.REFERENCE_TYPE_DEPENDENCY);

		portletDataContext.addClassedModel(
			assetDisplayPageElement,
			ExportImportPathUtil.getModelPath(assetDisplayPageEntry),
			assetDisplayPageEntry);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext,
			AssetDisplayPageEntry assetDisplayPageEntry)
		throws Exception {

		AssetDisplayPageEntry importedAssetDisplayPageEntry =
			(AssetDisplayPageEntry)assetDisplayPageEntry.clone();

		importedAssetDisplayPageEntry.setGroupId(
			portletDataContext.getScopeGroupId());

		AssetDisplayPageEntry existingAssetDisplayPageEntry =
			_stagedModelRepository.fetchStagedModelByUuidAndGroupId(
				assetDisplayPageEntry.getUuid(),
				portletDataContext.getScopeGroupId());

		if ((existingAssetDisplayPageEntry == null) ||
			!portletDataContext.isDataStrategyMirror()) {

			Element element = portletDataContext.getImportDataElement(
				assetDisplayPageEntry);

			String className = element.attributeValue("class-name");

			Map<Long, Long> classPKs =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					className);

			importedAssetDisplayPageEntry.setClassNameId(
				_portal.getClassNameId(className));

			importedAssetDisplayPageEntry.setClassPK(
				classPKs.get(assetDisplayPageEntry.getClassPK()));

			importedAssetDisplayPageEntry =
				_stagedModelRepository.addStagedModel(
					portletDataContext, importedAssetDisplayPageEntry);
		}
		else {
			importedAssetDisplayPageEntry.setAssetDisplayPageEntryId(
				existingAssetDisplayPageEntry.getAssetDisplayPageEntryId());
			importedAssetDisplayPageEntry.setClassPK(
				existingAssetDisplayPageEntry.getClassPK());
			importedAssetDisplayPageEntry.setLayoutPageTemplateEntryId(
				existingAssetDisplayPageEntry.getLayoutPageTemplateEntryId());

			importedAssetDisplayPageEntry =
				_stagedModelRepository.updateStagedModel(
					portletDataContext, importedAssetDisplayPageEntry);
		}

		portletDataContext.importClassedModel(
			assetDisplayPageEntry, importedAssetDisplayPageEntry);
	}

	@Override
	protected StagedModelRepository<AssetDisplayPageEntry>
		getStagedModelRepository() {

		return _stagedModelRepository;
	}

	@Reference
	private Portal _portal;

	@Reference(
		target = "(model.class.name=com.liferay.asset.display.page.model.AssetDisplayPageEntry)",
		unbind = "-"
	)
	private StagedModelRepository<AssetDisplayPageEntry> _stagedModelRepository;

}