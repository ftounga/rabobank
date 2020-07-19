#!/usr/bin/env bash
#docker-compose down --rmi all
docker-compose down
echo "docker-compose up"
#docker-compose up -d
# tounga.franck@ng-itconsulting.com
# force rebuild all image
docker-compose up --force-recreate --build
