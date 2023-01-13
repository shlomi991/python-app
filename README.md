# python-app

## Jenkins server

Run the jenkins server:
```
docker-compose -f jenkins/docker-compose.yml up -d
```
### Setup
Get the password to Jenkins from container logs:
```
docker logs jenkins
```
After you can open your Jenkins app in localhost port 8080 (http://localhost:8080/) and put your password.

Now, you need to create credentials for docker authentication in the pipeline.

go to:

Manage Jenkins > Manage credentials > System > Global credentials > Add credentials

Kind: Username and Password
Scope: Global
Username: $DOCKERHUB_USENAME
Password: $DOCKERHUB_PASSWORD
ID: dockerhubaccount



