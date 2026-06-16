pipeline {
    agent any

    environment {
        APP_NAME = 'springboot-demo'
        SONARQUBE_ENV = 'SonarQube'
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
                sh './gradlew clean test'
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
                sh 'docker build -t springboot-demo:latest .'
            }
        }

        stage('Deploy Local') {
            steps {
                sh '''
                    docker rm -f springboot-demo || true
                    docker run -d \
                      --name springboot-demo \
                      -p 8080:8080 \
                      springboot-demo:latest
                '''
            }
        }
    }
}