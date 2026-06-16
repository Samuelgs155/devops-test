pipeline {
    agent any

    environment {
        APP_NAME = 'springboot-demo'
        SONARQUBE_ENV = 'SonarQube'
        IMAGE_NAME = 'springboot-demo:latest'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew clean build'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv("${SONARQUBE_ENV}") {
                    sh './gradlew sonar'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 3, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t ${IMAGE_NAME} ."
            }
        }

        stage('Deploy Local') {
            steps {
                sh """
                    docker rm -f ${APP_NAME} || true
                    docker run -d \
                      --name ${APP_NAME} \
                      -p 8080:8080 \
                      ${IMAGE_NAME}
                """
            }
        }
    }
}