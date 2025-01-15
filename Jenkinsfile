pipeline {
    agent any

      tools {
            gradle 'Gradle'
        }

    environment {
        GRADLE_HOME = tool name: 'Gradle', type: 'ToolLocation'
        DOCKER_IMAGE = 'limit-control'
        DOCKER_TAG = 'limit-control'
    }

    stages {
        // Этап клонирования репозитория из GitHub
        stage('Checkout') {
            steps {
                git credentialsId: 'dd12', url: 'https://github.com/Dmiit3iy/limit-controle-microservice.git'
            }
        }

        // Этап сборки с Gradle
        stage('Build') {
            steps {
                script {
                    // Выполнение Gradle команды для сборки проекта
                    sh "'${GRADLE_HOME}/bin/gradle' clean bootJar"
                }
            }
        }

        // Этап тестирования
        stage('Test') {
            steps {
                script {
                    // Запуск тестов с Gradle
                    sh "'${GRADLE_HOME}/bin/gradle' test"
                }
            }
        }

        // Этап сборки Docker-образа
        stage('Docker Build') {
            steps {
                script {
                    // Сборка Docker-образа
                    sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                }
            }
        }

        // Этап деплоя с использованием Docker Compose
        stage('Docker Compose Up') {
            steps {
                script {
                    // Запуск контейнеров с использованием Docker Compose
                    sh 'docker-compose -f docker-compose.yml up -d'
                }
            }
        }

        // Этап остановки контейнеров после тестов (если необходимо)
        stage('Stop Containers') {
            steps {
                script {
                    // Остановка контейнеров Docker Compose
                    sh 'docker-compose -f docker-compose.yml down'
                }
            }
        }
    }

    post {
        success {
            // Уведомление об успешном завершении
            echo 'Build and deployment successful!'
        }
        failure {
            // Уведомление о неудаче
            echo 'Build failed!'
        }
    }
}
