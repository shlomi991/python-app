# python-app

## Jenkins server

For run the jenkins server:
```
docker-compose -f jenkins/docker-compose.yml up -d
```

Get the password to Jenkins from container logs:
```
docker logs jenkins
```
After you can open your Jenkins app in localhost port 8080 (http://localhost:8080/) and put your password.

Now, you need to create credentials for the docker authentication in the pipeline:
go to:
Manage Jenkins > Manage credentials
