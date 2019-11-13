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

package com.liferay.portal.tools.service.builder.test.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.sql.Blob;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link LazyBlobEntity}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LazyBlobEntity
 * @generated
 */
public class LazyBlobEntityWrapper
	implements LazyBlobEntity, ModelWrapper<LazyBlobEntity> {

	public LazyBlobEntityWrapper(LazyBlobEntity lazyBlobEntity) {
		_lazyBlobEntity = lazyBlobEntity;
	}

	@Override
	public Class<?> getModelClass() {
		return LazyBlobEntity.class;
	}

	@Override
	public String getModelClassName() {
		return LazyBlobEntity.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("lazyBlobEntityId", getLazyBlobEntityId());
		attributes.put("groupId", getGroupId());
		attributes.put("blob1", getBlob1());
		attributes.put("blob2", getBlob2());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long lazyBlobEntityId = (Long)attributes.get("lazyBlobEntityId");

		if (lazyBlobEntityId != null) {
			setLazyBlobEntityId(lazyBlobEntityId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Blob blob1 = (Blob)attributes.get("blob1");

		if (blob1 != null) {
			setBlob1(blob1);
		}

		Blob blob2 = (Blob)attributes.get("blob2");

		if (blob2 != null) {
			setBlob2(blob2);
		}
	}

	@Override
	public Object clone() {
		return new LazyBlobEntityWrapper(
			(LazyBlobEntity)_lazyBlobEntity.clone());
	}

	@Override
	public int compareTo(LazyBlobEntity lazyBlobEntity) {
		return _lazyBlobEntity.compareTo(lazyBlobEntity);
	}

	/**
	 * Returns the blob1 of this lazy blob entity.
	 *
	 * @return the blob1 of this lazy blob entity
	 */
	@Override
	public Blob getBlob1() {
		return _lazyBlobEntity.getBlob1();
	}

	/**
	 * Returns the blob2 of this lazy blob entity.
	 *
	 * @return the blob2 of this lazy blob entity
	 */
	@Override
	public Blob getBlob2() {
		return _lazyBlobEntity.getBlob2();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _lazyBlobEntity.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this lazy blob entity.
	 *
	 * @return the group ID of this lazy blob entity
	 */
	@Override
	public long getGroupId() {
		return _lazyBlobEntity.getGroupId();
	}

	/**
	 * Returns the lazy blob entity ID of this lazy blob entity.
	 *
	 * @return the lazy blob entity ID of this lazy blob entity
	 */
	@Override
	public long getLazyBlobEntityId() {
		return _lazyBlobEntity.getLazyBlobEntityId();
	}

	/**
	 * Returns the primary key of this lazy blob entity.
	 *
	 * @return the primary key of this lazy blob entity
	 */
	@Override
	public long getPrimaryKey() {
		return _lazyBlobEntity.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lazyBlobEntity.getPrimaryKeyObj();
	}

	/**
	 * Returns the uuid of this lazy blob entity.
	 *
	 * @return the uuid of this lazy blob entity
	 */
	@Override
	public String getUuid() {
		return _lazyBlobEntity.getUuid();
	}

	@Override
	public int hashCode() {
		return _lazyBlobEntity.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _lazyBlobEntity.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _lazyBlobEntity.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _lazyBlobEntity.isNew();
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a lazy blob entity model instance should use the <code>LazyBlobEntity</code> interface instead.
	 */
	@Override
	public void persist() {
		_lazyBlobEntity.persist();
	}

	/**
	 * Sets the blob1 of this lazy blob entity.
	 *
	 * @param blob1 the blob1 of this lazy blob entity
	 */
	@Override
	public void setBlob1(Blob blob1) {
		_lazyBlobEntity.setBlob1(blob1);
	}

	/**
	 * Sets the blob2 of this lazy blob entity.
	 *
	 * @param blob2 the blob2 of this lazy blob entity
	 */
	@Override
	public void setBlob2(Blob blob2) {
		_lazyBlobEntity.setBlob2(blob2);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_lazyBlobEntity.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_lazyBlobEntity.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_lazyBlobEntity.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_lazyBlobEntity.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this lazy blob entity.
	 *
	 * @param groupId the group ID of this lazy blob entity
	 */
	@Override
	public void setGroupId(long groupId) {
		_lazyBlobEntity.setGroupId(groupId);
	}

	/**
	 * Sets the lazy blob entity ID of this lazy blob entity.
	 *
	 * @param lazyBlobEntityId the lazy blob entity ID of this lazy blob entity
	 */
	@Override
	public void setLazyBlobEntityId(long lazyBlobEntityId) {
		_lazyBlobEntity.setLazyBlobEntityId(lazyBlobEntityId);
	}

	@Override
	public void setNew(boolean n) {
		_lazyBlobEntity.setNew(n);
	}

	/**
	 * Sets the primary key of this lazy blob entity.
	 *
	 * @param primaryKey the primary key of this lazy blob entity
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_lazyBlobEntity.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_lazyBlobEntity.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the uuid of this lazy blob entity.
	 *
	 * @param uuid the uuid of this lazy blob entity
	 */
	@Override
	public void setUuid(String uuid) {
		_lazyBlobEntity.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LazyBlobEntity>
		toCacheModel() {

		return _lazyBlobEntity.toCacheModel();
	}

	@Override
	public LazyBlobEntity toEscapedModel() {
		return new LazyBlobEntityWrapper(_lazyBlobEntity.toEscapedModel());
	}

	@Override
	public String toString() {
		return _lazyBlobEntity.toString();
	}

	@Override
	public LazyBlobEntity toUnescapedModel() {
		return new LazyBlobEntityWrapper(_lazyBlobEntity.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _lazyBlobEntity.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LazyBlobEntityWrapper)) {
			return false;
		}

		LazyBlobEntityWrapper lazyBlobEntityWrapper =
			(LazyBlobEntityWrapper)obj;

		if (Objects.equals(
				_lazyBlobEntity, lazyBlobEntityWrapper._lazyBlobEntity)) {

			return true;
		}

		return false;
	}

	@Override
	public LazyBlobEntity getWrappedModel() {
		return _lazyBlobEntity;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _lazyBlobEntity.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _lazyBlobEntity.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_lazyBlobEntity.resetOriginalValues();
	}

	private final LazyBlobEntity _lazyBlobEntity;

}