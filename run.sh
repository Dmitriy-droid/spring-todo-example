#!/usr/bin/env bash

cd "$(dirname "$0")" || exit

BROWSER_OPEN_URL=http://localhost:9000 bash -c ./spring-todo-*.jar
