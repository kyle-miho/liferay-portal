package com.liferay.asset.info.list.provider;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.info.pagination.Pagination;
import com.liferay.info.provider.InfoListProvider;
import com.liferay.info.provider.InfoListProviderContext;
import com.liferay.info.sort.Sort;
import com.liferay.portal.kernel.language.LanguageUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component(service = InfoListProvider.class)
public class AssetEntryInfoListProvider implements InfoListProvider<AssetEntry> {

	@Override
	public List<AssetEntry> getInfoList(
		InfoListProviderContext infoListProviderContext) {

		Group group =
			_getGroup(infoListProviderContext.getGroupOptional());

		AssetEntry assetEntry =
			_getAssetEntry(infoListProviderContext.getAssetEntryOptional());

		Layout layout =
			_getLayout(infoListProviderContext.getLayoutOptional());

		if (group != null) {
			return _assetEntryLocalService.getEntries(
				_getAssetEntryQuery(-1,-1, true));
		}

		return null;
	}

	@Override
	public List<AssetEntry> getInfoList(
		InfoListProviderContext infoListProviderContext, Pagination pagination,
		Sort sort) {

		Group group =
			_getGroup(infoListProviderContext.getGroupOptional());

		AssetEntry assetEntry =
			_getAssetEntry(infoListProviderContext.getAssetEntryOptional());

		Layout layout =
			_getLayout(infoListProviderContext.getLayoutOptional());

		if (group != null) {
			return _assetEntryLocalService.getEntries(
				_getAssetEntryQuery(
					pagination.getStart(), pagination.getEnd(),
					!sort.isReverse()));
		}

		return null;
	}

	@Override
	public int getInfoListCount(
		InfoListProviderContext infoListProviderContext) {

		Group group =
			_getGroup(infoListProviderContext.getGroupOptional());

		if (group != null) {
			return _assetEntryLocalService.getGroupEntries(
				group.getGroupId()).size();
		}

		return 0;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale,"embedded-assets");
	}

	private AssetEntry _getAssetEntry (Optional<AssetEntry> assetEntry) {
		if (_assetEntry != null) {
			return _assetEntry;
		}

		if (assetEntry.isPresent()) {
			System.out.println(
				"AssetEntryId = " +
				String.valueOf(assetEntry.get().getEntryId()));

			return assetEntry.get();
		}

		return null;
	}

	private Group _getGroup(Optional<Group> group) {
		if (_group != null) {
			return _group;
		}

		if (group.isPresent()) {
			System.out.println(
				"GroupId = " + String.valueOf(group.get().getGroupId()));

			return group.get();
		}

		return null;
	}

	private Layout _getLayout(Optional<Layout> layout) {
		if (_layout != null) {
			return _layout;
		}

		if (layout.isPresent()) {
			System.out.println(
				"LayoutId = " + String.valueOf(layout.get().getPlid()));

			return layout.get();
		}

		return null;
	}

	private AssetEntryQuery _getAssetEntryQuery(int start, int end, boolean asc) {
		AssetEntryQuery entryQuery = new AssetEntryQuery();

		entryQuery.setEnd(end);
		entryQuery.setExcludeZeroViewCount(true);
		entryQuery.setOrderByCol1("viewCount");
		//entryQuery.setOrderByType1("ASC");
		entryQuery.setOrderByType1(asc ? "ASC" : "DESC");
		entryQuery.setStart(start);

		return entryQuery;
	}

	private AssetEntry _assetEntry;
	private Company _company;
	private Group _group;
	private Layout _layout;

	@Reference
	AssetEntryLocalService _assetEntryLocalService;
}
