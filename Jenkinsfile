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

    }
}