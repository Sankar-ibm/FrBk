#!/bin/bash
# test
cr_namespace=$1
echo "Container Registry Namespace: $cr_namespace"

############################################################################
# Download and install IBM CLI                                             #
############################################################################
echo "Install IBM Cloud CLI"
curl -sL https://ibm.biz/idt-installer | bash

############################################################################
# Log into the IBM Cloud environment using apikey                          #
############################################################################
echo "Login to IBM Cloud using apikey"
ibmcloud config --check-version=false
ibmcloud login -a https://api.ng.bluemix.net --apikey $CLOUD_API_KEY -r us-south
if [ $? -ne 0 ]; then
  echo "Failed to authenticate to IBM Cloud"
  exit 1
fi

############################################################################
# tag the image as latest and with the custom tag                          #
############################################################################
"Tagging the image as ft-backend-dev:$DEPLOY_TIMESTAMP-$TRAVIS_BUILD_NUMBER-$TRAVIS_BRANCH and ft-backend-dev:latest"
docker tag ft-backend-dev:$DEPLOY_TIMESTAMP-$TRAVIS_BUILD_NUMBER-$TRAVIS_BRANCH us.icr.io/$cr_namespace/ft-backend-dev:$DEPLOY_TIMESTAMP-$TRAVIS_BUILD_NUMBER-$TRAVIS_BRANCH
docker tag ft-backend-dev:latest us.icr.io/$cr_namespace/ft-backend-dev:latest

############################################################################
# Log into the IBM Cloud container registry                                #
############################################################################
echo "Logging into IBM Cloud container registry"
ibmcloud cr login
if [ $? -ne 0 ]; then
  echo "Failed to authenticate to IBM Cloud container registry"
  exit 1
fi

############################################################################
# If the image exists in the container registry then delete it             #
# then recreate it                                                         #
############################################################################
echo "looking to see if the image exists"
echo "using command: ibmcloud cr images | grep us.icr.io/$cr_namespace/ft-backend-dev:$DEPLOY_TIMESTAMP-$TRAVIS_BUILD_NUMBER-$TRAVIS_BRANCH"

lookup_image=$( ibmcloud cr images | grep "us.icr.io/$cr_namespace/ft-backend-dev:$DEPLOY_TIMESTAMP-$TRAVIS_BUILD_NUMBER-$TRAVIS_BRANCH" )

echo "Now looking to see if the image exists"
echo "using command: ibmcloud cr images | grep us.icr.io/$cr_namespace/ft-backend-dev:latest"

lookup_image=$( ibmcloud cr images | grep "us.icr.io/$cr_namespace/ft-backend-dev:latest" )

############################################################################
# Push the image                                                           #
############################################################################
echo "Pushing image to registry"
#echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker login -u iamapikey -p $CLOUD_API_KEY us.icr.io
docker push us.icr.io/$cr_namespace/ft-backend-dev:$DEPLOY_TIMESTAMP-$TRAVIS_BUILD_NUMBER-$TRAVIS_BRANCH
docker push us.icr.io/$cr_namespace/ft-backend-dev:latest
############################################################################
# end script                                                               #
############################################################################
