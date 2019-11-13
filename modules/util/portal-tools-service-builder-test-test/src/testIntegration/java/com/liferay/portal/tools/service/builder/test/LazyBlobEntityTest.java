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

package com.liferay.portal.tools.service.builder.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.jdbc.OutputBlob;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;
import com.liferay.portal.tools.service.builder.test.model.LazyBlobEntity;
import com.liferay.portal.tools.service.builder.test.service.LazyBlobEntityLocalService;
import com.liferay.portal.tools.service.builder.test.service.persistence.LazyBlobEntityPersistence;
import com.liferay.portal.tools.service.builder.test.service.persistence.LazyBlobEntityUtil;

import java.io.ByteArrayInputStream;

import java.sql.Blob;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Kyle Miho
 */
@RunWith(Arquillian.class)
public class LazyBlobEntityTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.portal.tools.service.builder.test.service"));

	@Before
	public void setUp() throws Exception {
		_persistence = LazyBlobEntityUtil.getPersistence();

		Blob blob1 = new OutputBlob(new ByteArrayInputStream(new byte[10]), 10);

		Blob blob2 = new OutputBlob(new ByteArrayInputStream(new byte[10]), 10);

		_addLazyBlobEntity(blob1, blob2);

		EntityCacheUtil.clearCache();
	}

	@After
	public void tearDown() {
		_persistence.remove(_lazyBlobEntity);
	}

	@Test
	public void testOpenInputStreamMethodsGenerated() {
		_lazyBlobEntityLocalService.openBlob1InputStream(
			_lazyBlobEntity.getLazyBlobEntityId());

		_lazyBlobEntityLocalService.openBlob2InputStream(
			_lazyBlobEntity.getLazyBlobEntityId());
	}

	private void _addLazyBlobEntity(Blob blob1, Blob blob2) {
		LazyBlobEntity lazyBlobEntity = _persistence.create(
			RandomTestUtil.nextLong());

		lazyBlobEntity.setBlob1(blob1);
		lazyBlobEntity.setBlob2(blob2);

		_lazyBlobEntity = _persistence.update(lazyBlobEntity);
	}

	private LazyBlobEntity _lazyBlobEntity;

	@Inject
	private LazyBlobEntityLocalService _lazyBlobEntityLocalService;

	private LazyBlobEntityPersistence _persistence;

}