#!/usr/bin/env bash

set -o errexit
set -o nounset
set -o pipefail

cd "$(dirname "${BASH_SOURCE[0]}")"

function main {
	if [ -f ../playwright-custom/package.json ]
	then
		yarn --cwd ../playwright-custom install
	fi
}

main