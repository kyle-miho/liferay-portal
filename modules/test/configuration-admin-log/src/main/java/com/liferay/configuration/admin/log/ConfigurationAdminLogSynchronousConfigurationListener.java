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

package com.liferay.configuration.admin.log;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationEvent;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.cm.SynchronousConfigurationListener;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Rafael Praxedes
 */
@Component(service = SynchronousConfigurationListener.class)
public class ConfigurationAdminLogSynchronousConfigurationListener
	implements SynchronousConfigurationListener {

	@Override
	public void configurationEvent(ConfigurationEvent configurationEvent) {
		_serviceRegistrationMap.putIfAbsent(
			configurationEvent.getPid(),
			_bundleContext.registerService(
				ManagedService.class, props -> _log(configurationEvent),
				MapUtil.singletonDictionary(
					Constants.SERVICE_PID, configurationEvent.getPid())));
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	private void _log(ConfigurationEvent configurationEvent) {
		int type = configurationEvent.getType();

		if (type == ConfigurationEvent.CM_DELETED) {
			_log.info(
				"Configuration with pid " + configurationEvent.getPid() +
					" has been deleted");
		}
		else if (type == ConfigurationEvent.CM_UPDATED) {
			_log.info(
				"Configuration with pid " + configurationEvent.getPid() +
					" has been updated");
		}

		ServiceRegistration<ManagedService> serviceRegistration =
			_serviceRegistrationMap.remove(configurationEvent.getPid());

		serviceRegistration.unregister();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ConfigurationAdminLogSynchronousConfigurationListener.class);

	private BundleContext _bundleContext;
	private final Map<String, ServiceRegistration<ManagedService>>
		_serviceRegistrationMap = new ConcurrentHashMap<>();

}