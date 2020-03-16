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

package com.liferay.test.portlet.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.test.portlet.model.TestEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TestEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TestEntryCacheModel
	implements CacheModel<TestEntry>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestEntryCacheModel)) {
			return false;
		}

		TestEntryCacheModel testEntryCacheModel = (TestEntryCacheModel)obj;

		if ((entryId == testEntryCacheModel.entryId) &&
			(mvccVersion == testEntryCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, entryId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", entryId=");
		sb.append(entryId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TestEntry toEntityModel() {
		TestEntryImpl testEntryImpl = new TestEntryImpl();

		testEntryImpl.setMvccVersion(mvccVersion);
		testEntryImpl.setEntryId(entryId);
		testEntryImpl.setCompanyId(companyId);
		testEntryImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			testEntryImpl.setCreateDate(null);
		}
		else {
			testEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			testEntryImpl.setModifiedDate(null);
		}
		else {
			testEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			testEntryImpl.setName("");
		}
		else {
			testEntryImpl.setName(name);
		}

		if (description == null) {
			testEntryImpl.setDescription("");
		}
		else {
			testEntryImpl.setDescription(description);
		}

		testEntryImpl.setStatus(status);

		testEntryImpl.resetOriginalValues();

		return testEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		entryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(entryId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeInt(status);
	}

	public long mvccVersion;
	public long entryId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public int status;

}