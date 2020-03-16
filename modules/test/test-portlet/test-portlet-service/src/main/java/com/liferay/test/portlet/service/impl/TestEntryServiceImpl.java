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

package com.liferay.test.portlet.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.test.portlet.service.base.TestEntryServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the test entry remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.test.portlet.service.TestEntryService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TestEntryServiceBaseImpl
 */
@Component(
	property = {
		"json.web.service.context.name=testportlet",
		"json.web.service.context.path=TestEntry"
	},
	service = AopService.class
)
public class TestEntryServiceImpl extends TestEntryServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use <code>com.liferay.test.portlet.service.TestEntryServiceUtil</code> to access the test entry remote service.
	 */

}