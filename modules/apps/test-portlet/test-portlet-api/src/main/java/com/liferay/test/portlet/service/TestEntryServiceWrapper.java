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

package com.liferay.test.portlet.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TestEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see TestEntryService
 * @generated
 */
public class TestEntryServiceWrapper
	implements ServiceWrapper<TestEntryService>, TestEntryService {

	public TestEntryServiceWrapper(TestEntryService testEntryService) {
		_testEntryService = testEntryService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _testEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public TestEntryService getWrappedService() {
		return _testEntryService;
	}

	@Override
	public void setWrappedService(TestEntryService testEntryService) {
		_testEntryService = testEntryService;
	}

	private TestEntryService _testEntryService;

}