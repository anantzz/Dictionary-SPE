pipeline {
    agent any
    environment {
        DOCKER_IMAGE = "serialcomder/restaurant-management-spe:latest"
    }
    stages {
        stage('Clone Repository') {
            steps {
                // Clone the GitHub repository
                git branch: 'main', url: 'https://github.com/anantzz/restaurant-management-spe.git'
            }
        }
        stage('Build Application') {
            steps {
                // Use Maven to build the application
                sh 'mvn clean package'
            }
        }
        stage('Build Docker Image') {
            steps {
                // Build the Docker image
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }
        stage('Push Docker Image') {
            steps {
                // Push Docker image to Docker Hub
                withCredentials([usernamePassword(credentialsId: 'DockerHubCred', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin'
                    sh 'docker push $DOCKER_IMAGE'
                }
            }
        }
        stage('Deploy with Docker Compose') {
            steps {
                // Deploy using Docker Compose
                sh 'docker-compose up -d'
            }
        }
    }
}
