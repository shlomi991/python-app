# python-app

## Jenkins server

Run the jenkins server:
```
docker-compose -f jenkins/docker-compose.yml up -d
```
### Setup
Get Jenkins password by the container logs:
```
docker logs jenkins
```
After you can open your Jenkins app in localhost port 8080 (http://localhost:8080/) and enter the Jenkins password.

Now, you need to create credentials for docker authentication in the pipeline.

Go to:

Manage Jenkins > Manage credentials > System > Global credentials > Add credentials

Kind: Username and Password

Scope: Global

Username: $DOCKERHUB_USENAME

Password: $DOCKERHUB_PASSWORD

ID: dockerhubaccount



#### Then create your pipeline:

Go to:

New Item > Pipeline

First, Choose GitHub project and enter the GitHub project URL

Second, The build trigger that we choose is "Poll SCM", And we will schedule the scan depends our needs.

Third (Pipeline object), Choose "pipeline script from SCM" enter the GitHub project URL, And enter from which branch take the jenkins file from. After, Put the Script Path: buildNdeploy.groovy




