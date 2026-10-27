/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {defineConfig, devices} from '@playwright/test';
import * as dotenv from 'dotenv';

dotenv.config();
dotenv.config({override: true, path: '.env.local'});

const portalURL = process.env.PORTAL_URL || 'http://localhost:8080';

export default defineConfig({
	testDir: './tests',
	use: {
		...devices['Desktop Chrome'],
		baseURL: portalURL,
	},
	webServer: {
		command: './start_portal.sh',
		env: {LIFERAY_HTTP_PORT: new URL(portalURL).port || '8080'},
		gracefulShutdown: {signal: 'SIGTERM', timeout: 60 * 1000},
		reuseExistingServer: true,
		stdout: 'pipe',
		timeout: 20 * 60 * 1000,
		url: `${portalURL}/c/portal/status`,
	},
});
