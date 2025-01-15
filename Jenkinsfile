pipeline {
    agent any

    tools {
        gradle 'Gradle'
    }

    environment {
        DOCKER_IMAGE = 'limit-control'
        DOCKER_TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'dd12', url: 'https://github.com/Dmiit3iy/limit-controle-microservice.git'
            }
        }
    stage('Prepare Gradle') {
            steps {
                sh 'chmod +x ./gradlew'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean bootJar'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
            }
        }

        stage('Docker Compose Up') {
            steps {
                sh 'docker-compose -f docker-compose.yml up -d'
            }
        }

        stage('Stop Containers') {
            steps {
                sh 'docker-compose -f docker-compose.yml down --remove-orphans'
            }
        }
    }

    post {
        success {
            echo 'Build and deployment successful!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}