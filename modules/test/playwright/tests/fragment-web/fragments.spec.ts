/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../fixtures/apiHelpersTest';
import {fragmentsPagesTest} from './fixtures/fragmentPagesTest'
import {isolatedSiteTest} from '../../fixtures/isolatedSiteTest';
import getRandomString from '../../utils/getRandomString';

export const test = mergeTests(
	apiHelpersTest,
    fragmentsPagesTest,
	isolatedSiteTest
);

test(
	'Temp Kyle Test'
	, async({
		apiHelpers,
		page,
		fragmentsPage,

}) => {
	const site = await apiHelpers.headlessSite.createSite(getRandomString());

    await fragmentsPage.goto(site.friendlyUrlPath);

    await fragmentsPage.createFragmentSet('Test Fragment Set');

	await fragmentsPage.createFragment('Test Fragment Set', 'Test Fragment');
});
