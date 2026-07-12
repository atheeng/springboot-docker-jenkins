pipeline {
    agent any

    stages {

        stage('Maven Build') {
            steps {
                echo 'Maven build started'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Docker image build started'
                sh 'docker build -t springboot-docker-jenkins:jenkins-build .'
            }
        }

        stage('Docker Compose Deploy') {
            steps {
                echo 'Docker Compose deployment started'

                sh '''
                    docker compose -f docker-compose-deploy.yml down
                    docker compose -f docker-compose-deploy.yml up -d
                '''
            }
        }

    }
}