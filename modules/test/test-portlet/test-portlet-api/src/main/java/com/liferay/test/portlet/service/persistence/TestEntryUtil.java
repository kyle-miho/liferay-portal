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

package com.liferay.test.portlet.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.test.portlet.model.TestEntry;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the test entry service. This utility wraps <code>com.liferay.test.portlet.service.persistence.impl.TestEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TestEntryPersistence
 * @generated
 */
public class TestEntryUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(TestEntry testEntry) {
		getPersistence().clearCache(testEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, TestEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TestEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TestEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TestEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TestEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TestEntry update(TestEntry testEntry) {
		return getPersistence().update(testEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TestEntry update(
		TestEntry testEntry, ServiceContext serviceContext) {

		return getPersistence().update(testEntry, serviceContext);
	}

	/**
	 * Caches the test entry in the entity cache if it is enabled.
	 *
	 * @param testEntry the test entry
	 */
	public static void cacheResult(TestEntry testEntry) {
		getPersistence().cacheResult(testEntry);
	}

	/**
	 * Caches the test entries in the entity cache if it is enabled.
	 *
	 * @param testEntries the test entries
	 */
	public static void cacheResult(List<TestEntry> testEntries) {
		getPersistence().cacheResult(testEntries);
	}

	/**
	 * Creates a new test entry with the primary key. Does not add the test entry to the database.
	 *
	 * @param entryId the primary key for the new test entry
	 * @return the new test entry
	 */
	public static TestEntry create(long entryId) {
		return getPersistence().create(entryId);
	}

	/**
	 * Removes the test entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the test entry
	 * @return the test entry that was removed
	 * @throws NoSuchTestEntryException if a test entry with the primary key could not be found
	 */
	public static TestEntry remove(long entryId)
		throws com.liferay.test.portlet.exception.NoSuchTestEntryException {

		return getPersistence().remove(entryId);
	}

	public static TestEntry updateImpl(TestEntry testEntry) {
		return getPersistence().updateImpl(testEntry);
	}

	/**
	 * Returns the test entry with the primary key or throws a <code>NoSuchTestEntryException</code> if it could not be found.
	 *
	 * @param entryId the primary key of the test entry
	 * @return the test entry
	 * @throws NoSuchTestEntryException if a test entry with the primary key could not be found
	 */
	public static TestEntry findByPrimaryKey(long entryId)
		throws com.liferay.test.portlet.exception.NoSuchTestEntryException {

		return getPersistence().findByPrimaryKey(entryId);
	}

	/**
	 * Returns the test entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entryId the primary key of the test entry
	 * @return the test entry, or <code>null</code> if a test entry with the primary key could not be found
	 */
	public static TestEntry fetchByPrimaryKey(long entryId) {
		return getPersistence().fetchByPrimaryKey(entryId);
	}

	/**
	 * Returns all the test entries.
	 *
	 * @return the test entries
	 */
	public static List<TestEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the test entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of test entries
	 * @param end the upper bound of the range of test entries (not inclusive)
	 * @return the range of test entries
	 */
	public static List<TestEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the test entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of test entries
	 * @param end the upper bound of the range of test entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of test entries
	 */
	public static List<TestEntry> findAll(
		int start, int end, OrderByComparator<TestEntry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the test entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TestEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of test entries
	 * @param end the upper bound of the range of test entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of test entries
	 */
	public static List<TestEntry> findAll(
		int start, int end, OrderByComparator<TestEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the test entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of test entries.
	 *
	 * @return the number of test entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TestEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TestEntryPersistence, TestEntryPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TestEntryPersistence.class);

		ServiceTracker<TestEntryPersistence, TestEntryPersistence>
			serviceTracker =
				new ServiceTracker<TestEntryPersistence, TestEntryPersistence>(
					bundle.getBundleContext(), TestEntryPersistence.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}