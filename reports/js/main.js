let durationDataTable = document.getElementById("duration-data-table");

createTable(durationDataTable, durationData);

addDateText(document.getElementById("duration-data-date"), durationDataGeneratedDate);

Sortable.init();

window.onload = function () {
	var statusChangesRowHeader = getElementByXpath("//th[contains(.,'Average Duration')]");

	triggerEvent(statusChangesRowHeader, 'click');
}

function addDateText(element, date) {
	let dateText = document.createTextNode("Generated " + timeago.format(date) + " on " + date)

	element.appendChild(dateText);
}

function createDurationLineChart(data, elementID) {
	var durationLineChart = new Chart(document.getElementById(elementID), {
		type: 'line',
		data: {
			labels: getIndexArray(data.length),
			datasets: [
				{
					data: data,
					backgroundColor: 'rgba(54, 162, 235, 0.2)',
					borderColor: 'rgba(54, 162, 235, 1)',
					borderWidth: 1
				}
			]
		},
		options: {
			animation: false,
			elements: {
				line: {
					borderColor: '#000000',
					borderWidth: 1,
					tension: 0
				},
				point: {
					hitRadius: 10,
					hoverRadius: 4,
					radius: 0
				}
			},
			legend: {
				display: false
			},
			maintainAspectRatio: false,
			responsive: false,
			scales: {
				xAxes: [
					{
						display: false
					}
				],
				yAxes: [
					{
						display: false,
						ticks: {
							min: 0,
							max: 2500000
						}
					}
				]
			},
			tooltips: {
				callbacks: {
					title: function() {},
					label: function (tooltipItems, data) {
						return getMinutesString(tooltipItems.yLabel);
					}
				},
				custom: function(tooltip) {
					tooltip.displayColors = false;
				},
				intersect: false,
			}
		}
	});
}

function createTable(table, tableData) {
	let tbody = table.createTBody();

	tableData.forEach((rowData, index) => {
		if (index == 0) {
			let thead = table.createTHead();

			let row = thead.insertRow();

			rowData.forEach((columnHeader) => {
				let th = document.createElement("th");

				th.appendChild(document.createTextNode(columnHeader));

				row.appendChild(th);
			});
		}
		else {
			let row = tbody.insertRow();

			rowData.forEach((cellData, columnIndex) => {
				let cell = row.insertCell();

				if (Array.isArray(cellData)) {
					if (columnIndex == 2) {
						cellData.forEach((urlData) => {
							let anchorElement = document.createElement("a");

							anchorElement.classList.add("fa");

							if (urlData[0] == "PASSED") {
								anchorElement.classList.add("fa-check");
							}
							else {
								anchorElement.classList.add("fa-times");
							}

							anchorElement.href = urlData[1];

							anchorElement.target = "_blank";

							cell.appendChild(anchorElement);

							cell.appendChild(document.createTextNode(" "));

						});
					}
					if (columnIndex == 3) {
						let canvasElement = document.createElement("canvas");

						elementID = rowData[1] + "-" + rowData[0];

						canvasElement.id = elementID;

						canvasElement.setAttribute("height", 50);

						canvasElement.setAttribute("width", 300);

						cell.appendChild(canvasElement);

						createDurationLineChart(cellData, elementID);
					}
				}
				else {
					let node = null;

					if (typeof cellData === "string" || cellData instanceof String) {
						let divElement = document.createElement("div");

						let spanElement = document.createElement("span");

						if (columnIndex == 0) {
							cellData = cellData.replace("LocalFile.", "");

							cell.classList.add("col-1");
							cell.classList.add("truncate");
						}
						else if (columnIndex == 1) {
							cell.classList.add("col-2");
							cell.classList.add("truncate");

							if (cellData.includes("functional-")) {
								spanElement.classList.add("box-blue");

							}
							else if (cellData.includes("integration")) {
								spanElement.classList.add("box-red");
							}
							else {
								spanElement.classList.add("box-grey");
							}
						}
						else {
							cell.classList.add("nowrap");
						}

						spanElement.appendChild(document.createTextNode(cellData));

						divElement.appendChild(spanElement);

						divElement.setAttribute("data-value", cellData);

						node = divElement;
					}
					else {
						cell.setAttribute("data-value", cellData);

						node = document.createTextNode(getMinutesString(cellData));
					}

					cell.appendChild(node);
				}
			});
		}
	});
}

function getElementByXpath(path) {
	return document.evaluate(path, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
}

function getIndexArray(size) {
	return Array.from(Array(10).keys());
}

function getMinutesString(time) {
	var milliseconds = Math.floor((time % 1000));

	var seconds = Math.floor((time / 1000) % 60);

	var minutes = Math.floor((time / (60 * 1000)));

	return minutes + ":" + seconds.toString().padStart(2, "0") + "." + milliseconds.toString().padStart(3, "0");
}

function search() {
	let inputElement = document.getElementById("search");
	let tableElement = document.getElementById("duration-data-table");

	let searchFilter = inputElement.value.toUpperCase();
	let rowElements = tableElement.getElementsByTagName("tr");

	for (let i = 0; i < rowElements.length; i++) {
		let cellElements = rowElements[i].getElementsByTagName("td");

		for (let j = 0; j < cellElements.length; j++) {
			if (j < 2) {
				value = cellElements[j].textContent || cellElements[j].innerText;

				if (value.toUpperCase().indexOf(searchFilter) > -1) {
					rowElements[i].style.display = "";

					break;
				}
				else {
					rowElements[i].style.display = "none";
				}
			}
		}
	}
}

function triggerEvent(element, eventName) {
	element.dispatchEvent(new Event(eventName));
}
