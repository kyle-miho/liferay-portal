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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for TestEntry. This utility wraps
 * <code>com.liferay.test.portlet.service.impl.TestEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see TestEntryLocalService
 * @generated
 */
public class TestEntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.test.portlet.service.impl.TestEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the test entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testEntry the test entry
	 * @return the test entry that was added
	 */
	public static com.liferay.test.portlet.model.TestEntry addTestEntry(
		com.liferay.test.portlet.model.TestEntry testEntry) {

		return getService().addTestEntry(testEntry);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			createPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new test entry with the primary key. Does not add the test entry to the database.
	 *
	 * @param entryId the primary key for the new test entry
	 * @return the new test entry
	 */
	public static com.liferay.test.portlet.model.TestEntry createTestEntry(
		long entryId) {

		return getService().createTestEntry(entryId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the test entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the test entry
	 * @return the test entry that was removed
	 * @throws PortalException if a test entry with the primary key could not be found
	 */
	public static com.liferay.test.portlet.model.TestEntry deleteTestEntry(
			long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteTestEntry(entryId);
	}

	/**
	 * Deletes the test entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testEntry the test entry
	 * @return the test entry that was removed
	 */
	public static com.liferay.test.portlet.model.TestEntry deleteTestEntry(
		com.liferay.test.portlet.model.TestEntry testEntry) {

		return getService().deleteTestEntry(testEntry);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.test.portlet.model.impl.TestEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.test.portlet.model.impl.TestEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.test.portlet.model.TestEntry fetchTestEntry(
		long entryId) {

		return getService().fetchTestEntry(entryId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns a range of all the test entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.test.portlet.model.impl.TestEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of test entries
	 * @param end the upper bound of the range of test entries (not inclusive)
	 * @return the range of test entries
	 */
	public static java.util.List<com.liferay.test.portlet.model.TestEntry>
		getTestEntries(int start, int end) {

		return getService().getTestEntries(start, end);
	}

	/**
	 * Returns the number of test entries.
	 *
	 * @return the number of test entries
	 */
	public static int getTestEntriesCount() {
		return getService().getTestEntriesCount();
	}

	/**
	 * Returns the test entry with the primary key.
	 *
	 * @param entryId the primary key of the test entry
	 * @return the test entry
	 * @throws PortalException if a test entry with the primary key could not be found
	 */
	public static com.liferay.test.portlet.model.TestEntry getTestEntry(
			long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTestEntry(entryId);
	}

	/**
	 * Updates the test entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testEntry the test entry
	 * @return the test entry that was updated
	 */
	public static com.liferay.test.portlet.model.TestEntry updateTestEntry(
		com.liferay.test.portlet.model.TestEntry testEntry) {

		return getService().updateTestEntry(testEntry);
	}

	public static TestEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TestEntryLocalService, TestEntryLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TestEntryLocalService.class);

		ServiceTracker<TestEntryLocalService, TestEntryLocalService>
			serviceTracker =
				new ServiceTracker
					<TestEntryLocalService, TestEntryLocalService>(
						bundle.getBundleContext(), TestEntryLocalService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}