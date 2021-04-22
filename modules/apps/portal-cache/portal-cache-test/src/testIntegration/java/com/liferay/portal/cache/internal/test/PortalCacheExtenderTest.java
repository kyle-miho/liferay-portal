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

package com.liferay.portal.cache.internal.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.io.StreamUtil;
import com.liferay.petra.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.petra.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.cache.PortalCacheManagerNames;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.io.IOException;
import java.io.InputStream;

import java.lang.management.ManagementFactory;

import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;

/**
 * @author Kyle Miho
 */
@RunWith(Arquillian.class)
public class PortalCacheExtenderTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_multiVmXML = _generateXMLContent(1001, "test.cache.multi", 51);

		_singleVmXML = _generateXMLContent(1001, "test.cache.single", 51);

		_bundle = _installBundle(
			_BUNDLE_SYMBOLIC_NAME, _multiVmXML, _singleVmXML);
	}

	@After
	public void tearDown() throws Exception {
		if (_bundle.getState() != Bundle.UNINSTALLED) {
			_bundle.uninstall();
		}
	}

	@Test
	public void testAddAndRemoveConfig() throws Exception {
		_assertCacheConfig(
			PortalCacheManagerNames.MULTI_VM, false, 1001, "test.cache.multi",
			true, 51L);
		_assertCacheConfig(
			PortalCacheManagerNames.SINGLE_VM, false, 1001, "test.cache.single",
			true, 51L);

		_bundle.stop();

		_bundle.update(
			_createBundle(_BUNDLE_SYMBOLIC_NAME, null, _singleVmXML));

		_bundle.start();

		Assert.assertNull(
			_fetchMBeanObject(
				PortalCacheManagerNames.MULTI_VM, "test.cache.multi"));

		_assertCacheConfig(
			PortalCacheManagerNames.SINGLE_VM, false, 1001, "test.cache.single",
			true, 51L);

		_bundle.stop();

		_bundle.update(_createBundle(_BUNDLE_SYMBOLIC_NAME, _multiVmXML, null));

		_bundle.start();

		Assert.assertNull(
			_fetchMBeanObject(
				PortalCacheManagerNames.SINGLE_VM, "test.cache.single"));

		_assertCacheConfig(
			PortalCacheManagerNames.MULTI_VM, false, 1001, "test.cache.multi",
			true, 51L);
	}

	@Test
	public void testUpdateConfig() throws Exception {
		_assertCacheConfig(
			PortalCacheManagerNames.MULTI_VM, false, 1001, "test.cache.multi",
			true, 51L);
		_assertCacheConfig(
			PortalCacheManagerNames.SINGLE_VM, false, 1001, "test.cache.single",
			true, 51L);

		Bundle overridingBundle = null;

		String multiVmXMLUpdated = _generateXMLContent(
			2001, "test.cache.multi", 101);
		String singleVmXMLUpdated = _generateXMLContent(
			2001, "test.cache.single", 101);

		try {
			overridingBundle = _installBundle(
				_BUNDLE_SYMBOLIC_NAME.concat(".updated"), multiVmXMLUpdated,
				singleVmXMLUpdated);

			_assertCacheConfig(
				PortalCacheManagerNames.MULTI_VM, false, 2001,
				"test.cache.multi", true, 101L);
			_assertCacheConfig(
				PortalCacheManagerNames.SINGLE_VM, false, 2001,
				"test.cache.single", true, 101L);
		}
		finally {
			if ((overridingBundle != null) &&
				(overridingBundle.getState() != Bundle.UNINSTALLED)) {

				overridingBundle.uninstall();
			}
		}
	}

	private void _assertCacheConfig(
			String cacheManagerName, boolean eternal, int maxElementsInMemory,
			String name, boolean overflowToDisk, long timeToIdleSeconds)
		throws Exception {

		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

		ObjectName objectName = new ObjectName(
			StringBundler.concat(
				"net.sf.ehcache:type=CacheConfiguration,CacheManager=",
				cacheManagerName, ",name=", name));

		Assert.assertEquals(
			eternal, mBeanServer.getAttribute(objectName, "Eternal"));
		Assert.assertEquals(
			maxElementsInMemory,
			mBeanServer.getAttribute(objectName, "MaxElementsInMemory"));
		Assert.assertEquals(name, mBeanServer.getAttribute(objectName, "Name"));
		Assert.assertEquals(
			overflowToDisk,
			mBeanServer.getAttribute(objectName, "OverflowToDisk"));
		Assert.assertEquals(
			timeToIdleSeconds,
			mBeanServer.getAttribute(objectName, "TimeToIdleSeconds"));
	}

	private InputStream _createBundle(
			String bundleSymbolicName, String multiCacheConfigContent,
			String singleCacheConfigContent)
		throws Exception {

		try (UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
				new UnsyncByteArrayOutputStream()) {

			try (JarOutputStream jarOutputStream = new JarOutputStream(
					unsyncByteArrayOutputStream)) {

				_writeManifest(bundleSymbolicName, "1.0.0", jarOutputStream);

				_writeClass(jarOutputStream);

				if (multiCacheConfigContent != null) {
					_writeResource(
						jarOutputStream, multiCacheConfigContent,
						"META-INF/module-multi-vm.xml");
				}

				if (singleCacheConfigContent != null) {
					_writeResource(
						jarOutputStream, singleCacheConfigContent,
						"META-INF/module-single-vm.xml");
				}
			}

			return new UnsyncByteArrayInputStream(
				unsyncByteArrayOutputStream.unsafeGetByteArray(), 0,
				unsyncByteArrayOutputStream.size());
		}
	}

	private Object _fetchMBeanObject(String cacheManagerName, String cacheName)
		throws Exception {

		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

		Object object = null;

		try {
			object = mBeanServer.getObjectInstance(
				new ObjectName(
					StringBundler.concat(
						"net.sf.ehcache:type=CacheConfiguration,CacheManager=",
						cacheManagerName, ",name=t", cacheName)));
		}
		catch (InstanceNotFoundException instanceNotFoundException) {
		}

		return object;
	}

	private String _generateXMLContent(
		int maxElementsInMemory, String cacheName, int timeToIdleSeconds) {

		StringBundler sb = new StringBundler(11);

		sb.append("<ehcache dynamicConfig=\"true\" monitoring=\"off\" ");
		sb.append("updateCheck=\"false\" xmlns:xsi=\"http://www.w3.org/2001");
		sb.append("/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"");
		sb.append("http://www.ehcache.org/ehcache.xsd\">");
		sb.append("<cache eternal=\"false\" maxElementsInMemory=\"");
		sb.append(maxElementsInMemory);
		sb.append("\" name=\"");
		sb.append(cacheName);
		sb.append("\" overflowToDisk=\"true\" timeToIdleSeconds=\"");
		sb.append(timeToIdleSeconds);
		sb.append("\"> </cache> </ehcache>");

		return sb.toString();
	}

	private Bundle _installBundle(
			String bundleSymbolicName, String multiCacheConfigContent,
			String singleCacheConfigContent)
		throws Exception {

		Bundle bundle = FrameworkUtil.getBundle(PortalCacheExtenderTest.class);

		BundleContext bundleContext = bundle.getBundleContext();

		Bundle newBundle = bundleContext.installBundle(
			bundleSymbolicName,
			_createBundle(
				bundleSymbolicName, multiCacheConfigContent,
				singleCacheConfigContent));

		newBundle.start();

		return newBundle;
	}

	private void _writeClass(JarOutputStream jarOutputStream)
		throws IOException {

		String className = PortalCacheExtenderTest.class.getName();

		String path = StringUtil.replace(
			className, CharPool.PERIOD, CharPool.SLASH);

		String resourcePath = path.concat(".class");

		jarOutputStream.putNextEntry(new ZipEntry(resourcePath));

		ClassLoader classLoader =
			PortalCacheExtenderTest.class.getClassLoader();

		StreamUtil.transfer(
			classLoader.getResourceAsStream(resourcePath), jarOutputStream,
			false);

		jarOutputStream.closeEntry();
	}

	private void _writeManifest(
			String bundleSymbolicName, String bundleVersion,
			JarOutputStream jarOutputStream)
		throws IOException {

		Manifest manifest = new Manifest();

		Attributes attributes = manifest.getMainAttributes();

		attributes.putValue(Constants.BUNDLE_MANIFESTVERSION, "2");
		attributes.putValue(Constants.BUNDLE_SYMBOLICNAME, bundleSymbolicName);
		attributes.putValue(Constants.BUNDLE_VERSION, bundleVersion);
		attributes.putValue("Manifest-Version", "1");

		jarOutputStream.putNextEntry(new ZipEntry(JarFile.MANIFEST_NAME));

		manifest.write(jarOutputStream);

		jarOutputStream.closeEntry();
	}

	private void _writeResource(
			JarOutputStream jarOutputStream, String content, String outputPath)
		throws IOException {

		jarOutputStream.putNextEntry(new ZipEntry(outputPath));

		StreamUtil.transfer(
			new UnsyncByteArrayInputStream(content.getBytes()), jarOutputStream,
			false);

		jarOutputStream.closeEntry();
	}

	private static final String _BUNDLE_SYMBOLIC_NAME =
		"com.liferay.portal.cache.internal.test.PortalCacheTestModule";

	private static Bundle _bundle;
	private static String _multiVmXML;
	private static String _singleVmXML;

}