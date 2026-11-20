#!/usr/bin/env bash

set -o errexit
set -o nounset
set -o pipefail

cd "$(dirname "${BASH_SOURCE[0]}")"

function main {
	NODE_PATH="${PWD}/node_modules" node_modules/.bin/playwright test "${@}"
}

main "${@}"