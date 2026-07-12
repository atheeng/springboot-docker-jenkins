// pipeline {
//     agent any
//
//     stages {
//
//         stage('Maven Build') {
//             steps {
//                 echo 'Maven build started'
//                 sh 'mvn clean package -DskipTests'
//             }
//         }
//
//         stage('Docker Build') {
//             steps {
//                 echo 'Docker image build started'
//                 sh 'docker build -t springboot-docker-jenkins:jenkins-build .'
//             }
//         }
//
//         stage('Docker Compose Deploy') {
//             steps {
//                 echo 'Docker Compose deployment started'
//
//               sh '''
//                   docker compose -p springboot-mongodb-crud -f docker-compose-deploy.yml down
//                   docker compose -p springboot-mongodb-crud -f docker-compose-deploy.yml up -d
//               '''
//             }
//
//         }
//
//     }
// }

pipeline {
    agent any

    environment {
        PROJECT_NAME = 'springboot-mongodb-crud'
        COMPOSE_FILE = 'docker-compose-deploy.yml'
        IMAGE_NAME = 'springboot-docker-jenkins:jenkins-build'
        DOCKERHUB_IMAGE = '9841141368/springboot-docker-jenkins:jenkins-build'
    }

    options {
        disableConcurrentBuilds()
        timeout(time: 10, unit: 'MINUTES')
    }

    stages {

        stage('Maven Build') {
            steps {
                echo '1. Build Spring Boot JAR'

                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo '2. Build Docker image'

                sh 'docker build -t "$IMAGE_NAME" .'
            }
        }

        stage('Docker Hub Push') {
            steps {
                echo '3. Push Docker image to Docker Hub'

                withCredentials([
                            usernamePassword(
                                credentialsId: 'dockerhub-credentials',
                                usernameVariable: 'DOCKERHUB_USERNAME',
                                passwordVariable: 'DOCKERHUB_TOKEN'
                            )
                    ]) {
                    sh '''
                        echo "$DOCKERHUB_TOKEN" | \
                        docker login \
                          --username "$DOCKERHUB_USERNAME" \
                          --password-stdin

                        docker tag "$IMAGE_NAME" "$DOCKERHUB_IMAGE"

                        docker push "$DOCKERHUB_IMAGE"

                        docker logout
                    '''
                }
            }
        }

        stage('Validate Compose') {
            steps {
                echo '4. Validate Compose file'

                sh '''
                    docker compose \
                      -p "$PROJECT_NAME" \
                      -f "$COMPOSE_FILE" \
                      config
                '''
            }
        }

        // remaining existing stages...
    }

    post {
        success {
            echo 'CI/CD pipeline completed successfully'
        }

        failure {
            echo 'CI/CD pipeline failed'
        }

        always {
            echo 'Pipeline execution finished'
        }
    }
}