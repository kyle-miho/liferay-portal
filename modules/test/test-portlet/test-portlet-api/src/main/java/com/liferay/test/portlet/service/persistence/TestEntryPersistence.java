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

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.test.portlet.exception.NoSuchTestEntryException;
import com.liferay.test.portlet.model.TestEntry;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the test entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TestEntryUtil
 * @generated
 */
@ProviderType
public interface TestEntryPersistence extends BasePersistence<TestEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestEntryUtil} to access the test entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the test entry in the entity cache if it is enabled.
	 *
	 * @param testEntry the test entry
	 */
	public void cacheResult(TestEntry testEntry);

	/**
	 * Caches the test entries in the entity cache if it is enabled.
	 *
	 * @param testEntries the test entries
	 */
	public void cacheResult(java.util.List<TestEntry> testEntries);

	/**
	 * Creates a new test entry with the primary key. Does not add the test entry to the database.
	 *
	 * @param entryId the primary key for the new test entry
	 * @return the new test entry
	 */
	public TestEntry create(long entryId);

	/**
	 * Removes the test entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the test entry
	 * @return the test entry that was removed
	 * @throws NoSuchTestEntryException if a test entry with the primary key could not be found
	 */
	public TestEntry remove(long entryId) throws NoSuchTestEntryException;

	public TestEntry updateImpl(TestEntry testEntry);

	/**
	 * Returns the test entry with the primary key or throws a <code>NoSuchTestEntryException</code> if it could not be found.
	 *
	 * @param entryId the primary key of the test entry
	 * @return the test entry
	 * @throws NoSuchTestEntryException if a test entry with the primary key could not be found
	 */
	public TestEntry findByPrimaryKey(long entryId)
		throws NoSuchTestEntryException;

	/**
	 * Returns the test entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entryId the primary key of the test entry
	 * @return the test entry, or <code>null</code> if a test entry with the primary key could not be found
	 */
	public TestEntry fetchByPrimaryKey(long entryId);

	/**
	 * Returns all the test entries.
	 *
	 * @return the test entries
	 */
	public java.util.List<TestEntry> findAll();

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
	public java.util.List<TestEntry> findAll(int start, int end);

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
	public java.util.List<TestEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestEntry>
			orderByComparator);

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
	public java.util.List<TestEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TestEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the test entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of test entries.
	 *
	 * @return the number of test entries
	 */
	public int countAll();

}