pipeline {
    agent any

    tools {
        gradle 'Gradle'
    }

    environment {
        DOCKER_IMAGE = 'limit-control'
        DOCKER_TAG = 'latest'
        POSTGRES_USER = 'postgres'
        POSTGRES_PASSWORD = 'root'
        POSTGRES_DB = 'limit_microservice'
        POSTGRES_PORT = '5432'
    }

    stages {

        stage('Start PostgreSQL Container') {
            steps {
                 script {
                            // Запуск контейнера PostgreSQL
                     docker.image('postgres:latest').withRun("-e POSTGRES_USER=${POSTGRES_USER} -e POSTGRES_PASSWORD=${POSTGRES_PASSWORD} -e POSTGRES_DB=${POSTGRES_DB} -p ${POSTGRES_PORT}:${POSTGRES_PORT}") { c ->
                                // Действия после запуска контейнера, например, можно подождать, пока PostgreSQL будет готов
                           echo "PostgreSQL контейнер запущен на порту ${POSTGRES_PORT}"
                     }
                 }
            }
        }


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