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

package com.liferay.jenkins.results.parser;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

/**
 * @author Kenji Heigel
 */
public class TestDurationReportGenerator {

	// Set PORTAL_DIR to your local portal dir before using
	public static final String PORTAL_DIR =
		"/home/kyle/Liferay/repos/master-portal";

	public static File reportDir = new File(PORTAL_DIR + "/reports");

	public static void main(String[] args) throws IOException {
		_generateDependencyFiles();

		String buildURL =
			"https://test-1-7.liferay.com/job/test-portal-acceptance-pullrequest(master)/7127/";

		testHistoryMap = new TestHistoryMap(_getBuildResultJSONObject(buildURL));

		// You can load as many different builds as you like
		// String anotherURL = "http://test-1-1/job/test-portal-acceptance-pullrequest(master)/6609/";

		// testHistoryMap.populate(_getBuildResultJSONObject(anotherURL));

		// Use testBatchNameRegex to filter test batches. (e.g. functional-.*
		// or modules-integration-.* or .*(functional|unit).*)

		String testBatchNameRegex = ".*";

		// If you want all the results, use the configuration method below.
		// Otherwise, it will only show tests that take longer than 30000ms by
		// default.

		testHistoryMap.setMinimumTestDuration(0);

		testHistoryMap.writeDurationDataJavaScriptFile(
			reportDir.toString() + "/js/test-duration-data.js",
			testBatchNameRegex);

		System.out.println("Report available at: " + reportDir.toString() + "/index.html");
	}

	private static JSONObject _getBuildResultJSONObject(String buildURL) throws IOException {
		return JenkinsResultsParserUtil.toJSONObject(
			JenkinsResultsParserUtil.getBuildArtifactURL(
				buildURL, "build-result.json"));
	}

	private static void _generateDependencyFiles() throws IOException {
		File jsDir = new File(reportDir, "/js");

		jsDir.mkdirs();

		File cssDir = new File(reportDir, "/css");

		cssDir.mkdirs();

		String durationReportUrl =
			"http://mirrors.lax.liferay.com/github.com/liferay/" +
				"liferay-jenkins-ee/resources/reports/test-duration-report";

		JenkinsResultsParserUtil.write(
			reportDir.toString() + "/index.html",
			JenkinsResultsParserUtil.toString(
				durationReportUrl + "/index.html"));

		JenkinsResultsParserUtil.write(
			reportDir.toString() + "/css/main.css",
			JenkinsResultsParserUtil.toString(
				durationReportUrl + "/css/main.css"));

		JenkinsResultsParserUtil.write(
			reportDir.toString() + "/js/main.js",
			JenkinsResultsParserUtil.toString(
				durationReportUrl + "/js/main.js"));
	}

	public static TestHistoryMap testHistoryMap;
}
