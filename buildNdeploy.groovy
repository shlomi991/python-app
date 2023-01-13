
import groovy.json.JsonSlurperClassic
@NonCPS
def parseJsonToMap(String json) {
    final slurper = new JsonSlurperClassic()
    return new HashMap<>(slurper.parseText(json))
}

pipeline {
    agent any
    parameters {
        string(name: 'branch', defaultValue: 'main', description: 'Branch to build code from?', trim: true)
    }
    
    environment 
    {
        DOCKERHUB_CREDENTIALS=credentials('dockerhubaccount')
        USER_DOCKER = "shlomi99"
        RELEASE_NAME = "python"
        IMAGE_TAG = "${env.BUILD_ID}"
    }
    
    stages
    {
      stage('Checkout code') {
        steps {
          checkout scmGit(branches: [[name: 'main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/shlomi991/python-app.git']])
        }
      }
      stage('Docker build')
      {
        steps
        {
            sh 'env'
            sh '''
                  export IMAGE_REPOSITORY=${USER_DOCKER}/${RELEASE_NAME}

                  echo "IMAGE_REPOSITORY:" $IMAGE_REPOSITORY
                  echo "IMAGE_TAG:" $IMAGE_TAG

                  echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin
                  docker pull $IMAGE_REPOSITORY:latest 
                  docker build -t $IMAGE_REPOSITORY:$IMAGE_TAG -t $IMAGE_REPOSITORY:latest .
                  docker push $IMAGE_REPOSITORY:$IMAGE_TAG
                  docker push $IMAGE_REPOSITORY:latest
                '''
        }
      }

      stage('Deploy') {
        steps 
        {
            sh '''
                  docker run ${USER_DOCKER}/${RELEASE_NAME}:$IMAGE_TAG
                  docker logout
                '''
       }
     }
    }

    post {
        failure {
            script
            {
                echo "Failed"
            }
        }
        success {
            script 
            {
                echo "The app is deployed"
            }
        }
        always { 
            cleanWs()
        }
    }
  }
