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

import ClayLabel from '@clayui/label';
import moment from 'moment';
import React from 'react';
import ListView from '../../components/list-view/ListView.es';
import {confirmDelete} from '../../utils/client.es';

const APPS = {
	ACTIONS: [
		{
			callback: confirmDelete('/o/app-builder/v1.0/apps/'),
			name: Liferay.Language.get('delete')
		}
	],
	COLUMNS: [
		{
			key: 'name',
			sortable: true,
			value: Liferay.Language.get('name')
		},
		{
			key: 'type',
			value: Liferay.Language.get('deployed-as')
		},
		{
			key: 'dateCreated',
			sortable: true,
			value: Liferay.Language.get('create-date')
		},
		{
			asc: false,
			key: 'dateModified',
			sortable: true,
			value: Liferay.Language.get('modified-date')
		},
		{
			key: 'status',
			value: Liferay.Language.get('status')
		}
	],
	EMPTY_STATE: {
		empty: {
			description: Liferay.Language.get(
				'select-the-form-and-table-view-you-want-and-deploy-your-app-as-a-widget-standalone-or-place-it-in-the-product-menu'
			),
			title: Liferay.Language.get('there-are-no-deployments-yet')
		}
	},
	FORMATTER: items =>
		items.map(item => ({
			dateCreated: moment(item.dateCreated).fromNow(),
			dateModified: moment(item.dateModified).fromNow(),
			id: item.id,
			name: item.name.en_US,
			status: (
				<ClayLabel
					displayType={
						item.settings.deploymentStatus.toLowerCase() ===
						'deployed'
							? 'success'
							: 'secondary'
					}
				>
					{item.settings.deploymentStatus.toUpperCase()}
				</ClayLabel>
			),
			type: item.settings.deploymentTypes.reduce(
				(accumulator, currentValue, index) =>
					accumulator +
					(index === item.settings.deploymentTypes.length - 1
						? ' and '
						: ', ') +
					currentValue
			)
		}))
};

export default ({
	match: {
		params: {dataDefinitionId}
	}
}) => {
	return (
		<ListView
			actions={APPS.ACTIONS}
			columns={APPS.COLUMNS}
			emptyState={APPS.EMPTY_STATE}
			endpoint={`/o/app-builder/v1.0/data-definitions/${dataDefinitionId}/apps`}
			formatter={APPS.FORMATTER}
		/>
	);
};
