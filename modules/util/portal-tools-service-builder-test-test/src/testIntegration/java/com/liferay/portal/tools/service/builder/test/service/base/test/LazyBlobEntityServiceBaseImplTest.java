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

package com.liferay.portal.tools.service.builder.test.service.base.test;

import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.tools.service.builder.test.service.base.EagerBlobEntityLocalServiceBaseImpl;
import com.liferay.portal.tools.service.builder.test.service.base.LazyBlobEntityLocalServiceBaseImpl;
import com.liferay.portal.tools.service.builder.test.service.base.LazyBlobEntityServiceBaseImpl;
import com.liferay.portal.tools.service.builder.test.service.base.NestedSetsTreeEntryLocalServiceBaseImpl;

import java.io.Serializable;

import org.junit.Test;

/**
 * @author Kyle Miho
 */
public class LazyBlobEntityServiceBaseImplTest {

	@Test
	public void testActivateMethodGeneratedInLocalBaseServiceImpl() {
		_assertActivateMethodGenerated(
			LazyBlobEntityLocalServiceBaseImpl.class);
	}

	@Test(expected = NoSuchMethodException.class)
	public void testActivateMethodMissingInBaseServiceImpl() {
		_assertActivateMethodGenerated(LazyBlobEntityServiceBaseImpl.class);
	}

	@Test(expected = NoSuchMethodException.class)
	public void testActivateMethodMissingInEagerBlob() {
		_assertActivateMethodGenerated(
			EagerBlobEntityLocalServiceBaseImpl.class);
	}

	@Test(expected = NoSuchMethodException.class)
	public void testActivateMethodMissingWithNoBlob() {
		_assertActivateMethodGenerated(
			NestedSetsTreeEntryLocalServiceBaseImpl.class);
	}

	@Test
	public void testFileFieldGeneratedInLocalServiceBaseImpl() {
		_assertFileFieldGenerated(LazyBlobEntityLocalServiceBaseImpl.class);
	}

	@Test(expected = NoSuchFieldException.class)
	public void testFileFieldMissingInBaseServiceImpl() {
		_assertFileFieldGenerated(LazyBlobEntityServiceBaseImpl.class);
	}

	@Test(expected = NoSuchFieldException.class)
	public void testFileFieldMissingInEagerBlob() {
		_assertFileFieldGenerated(EagerBlobEntityLocalServiceBaseImpl.class);
	}

	@Test(expected = NoSuchFieldException.class)
	public void testFileFieldMissingWithNoBlob() {
		_assertFileFieldGenerated(
			NestedSetsTreeEntryLocalServiceBaseImpl.class);
	}

	@Test
	public void testGetBlobModelMethodsGeneratedInLocalServiceBaseImpl() {
		_assertGetBlobModelMethodGenerated(
			LazyBlobEntityLocalServiceBaseImpl.class, "blob1");
		_assertGetBlobModelMethodGenerated(
			LazyBlobEntityLocalServiceBaseImpl.class, "blob2");
	}

	@Test(expected = NoSuchMethodException.class)
	public void testGetBlobModelMethodsMissingInEagerBlob() {
		_assertGetBlobModelMethodGenerated(
			EagerBlobEntityLocalServiceBaseImpl.class, "blob1");
		_assertGetBlobModelMethodGenerated(
			EagerBlobEntityLocalServiceBaseImpl.class, "blob2");
	}

	@Test(expected = NoSuchMethodException.class)
	public void testGetBlobModelMethodsMissingInServiceBaseImpl() {
		_assertGetBlobModelMethodGenerated(
			LazyBlobEntityServiceBaseImpl.class, "blob1");
		_assertGetBlobModelMethodGenerated(
			LazyBlobEntityServiceBaseImpl.class, "blob2");
	}

	@Test
	public void testOpenInputStreamMethodsGeneratedInLocalServiceBaseImpl() {
		_assertOpenInputStreamMethodsGenerated(
			LazyBlobEntityLocalServiceBaseImpl.class, "blob1");
		_assertOpenInputStreamMethodsGenerated(
			LazyBlobEntityLocalServiceBaseImpl.class, "blob2");
	}

	@Test(expected = NoSuchMethodException.class)
	public void testOpenInputStreamMethodsMissingInEagerBlob() {
		_assertOpenInputStreamMethodsGenerated(
			EagerBlobEntityLocalServiceBaseImpl.class, "blob1");
		_assertOpenInputStreamMethodsGenerated(
			EagerBlobEntityLocalServiceBaseImpl.class, "blob2");
	}

	@Test(expected = NoSuchMethodException.class)
	public void testOpenInputStreamMethodsMissingInServiceBaseImpl() {
		_assertOpenInputStreamMethodsGenerated(
			LazyBlobEntityServiceBaseImpl.class, "blob1");
		_assertOpenInputStreamMethodsGenerated(
			LazyBlobEntityServiceBaseImpl.class, "blob2");
	}

	@Test
	public void testUseTempFileFieldGeneratedInLocalServiceBaseImpl() {
		_assertUseTempFileFieldGenerated(
			LazyBlobEntityLocalServiceBaseImpl.class);
	}

	@Test(expected = NoSuchFieldException.class)
	public void testUseTempFileFieldMissingInEagerBlob() {
		_assertUseTempFileFieldGenerated(
			EagerBlobEntityLocalServiceBaseImpl.class);
	}

	@Test(expected = NoSuchFieldException.class)
	public void testUseTempFileFieldMissingInServiceBaseImpl() {
		_assertUseTempFileFieldGenerated(LazyBlobEntityServiceBaseImpl.class);
	}

	@Test(expected = NoSuchFieldException.class)
	public void testUseTempFileFieldMissingWithNoBlob() {
		_assertUseTempFileFieldGenerated(
			NestedSetsTreeEntryLocalServiceBaseImpl.class);
	}

	private void _assertActivateMethodGenerated(Class<?> clazz) {
		ReflectionTestUtil.getMethod(clazz, "activate", new Class<?>[0]);
	}

	private void _assertFileFieldGenerated(Class<?> clazz) {
		ReflectionTestUtil.getField(clazz, "_file");
	}

	private void _assertGetBlobModelMethodGenerated(
		Class<?> clazz, String columnName) {

		String methodName = StringBundler.concat(
			"get", _capitalize(columnName), "BlobModel");

		ReflectionTestUtil.getMethod(
			clazz, methodName, new Class<?>[] {Serializable.class});
	}

	private void _assertOpenInputStreamMethodsGenerated(
		Class<?> clazz, String columnName) {

		String methodName = StringBundler.concat(
			"open", _capitalize(columnName), "InputStream");

		ReflectionTestUtil.getMethod(
			clazz, methodName, new Class<?>[] {Long.TYPE});
	}

	private void _assertUseTempFileFieldGenerated(Class<?> clazz) {
		ReflectionTestUtil.getField(clazz, "_useTempFile");
	}

	private String _capitalize(String s) {
		char firstChar = s.charAt(0);

		if (Character.isLowerCase(firstChar)) {
			s = Character.toUpperCase(firstChar) + s.substring(1);
		}

		return s;
	}

}