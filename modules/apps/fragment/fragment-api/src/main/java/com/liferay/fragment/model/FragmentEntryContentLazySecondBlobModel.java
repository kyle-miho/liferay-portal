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

package com.liferay.fragment.model;

import java.sql.Blob;

/**
 * The Blob model class for lazy loading the contentLazySecond column in FragmentEntry.
 *
 * @author Brian Wing Shun Chan
 * @see FragmentEntry
 * @generated
 */
public class FragmentEntryContentLazySecondBlobModel {

	public FragmentEntryContentLazySecondBlobModel() {
	}

	public FragmentEntryContentLazySecondBlobModel(long fragmentEntryId) {
		_fragmentEntryId = fragmentEntryId;
	}

	public FragmentEntryContentLazySecondBlobModel(
		long fragmentEntryId, Blob contentLazySecondBlob) {

		_fragmentEntryId = fragmentEntryId;
		_contentLazySecondBlob = contentLazySecondBlob;
	}

	public long getFragmentEntryId() {
		return _fragmentEntryId;
	}

	public void setFragmentEntryId(long fragmentEntryId) {
		_fragmentEntryId = fragmentEntryId;
	}

	public Blob getContentLazySecondBlob() {
		return _contentLazySecondBlob;
	}

	public void setContentLazySecondBlob(Blob contentLazySecondBlob) {
		_contentLazySecondBlob = contentLazySecondBlob;
	}

	private long _fragmentEntryId;
	private Blob _contentLazySecondBlob;

}