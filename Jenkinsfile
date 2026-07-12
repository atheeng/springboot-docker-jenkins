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
        stage('Start Infrastructure') {
            steps {
                echo '5. Start infrastructure services'

                sh '''
            docker compose \
              -p "$PROJECT_NAME" \
              -f "$COMPOSE_FILE" \
              up -d mongodb redis kafka kafka-ui
        '''
            }
        }

        stage('Wait for Kafka') {
            steps {
                echo 'Waiting for Kafka to start'

                sh 'sleep 15'
            }
        }

        stage('Remove Old Application') {
            steps {
                echo '6. Remove old Spring Boot container'

                sh '''
            docker rm -f springboot-mongodb-crud-container \
            2>/dev/null || true
        '''
            }
        }

        stage('Deploy Application') {
            steps {
                echo '7. Deploy new Spring Boot application'

                sh '''
            docker compose \
              -p "$PROJECT_NAME" \
              -f "$COMPOSE_FILE" \
              up -d \
              --no-deps \
              --force-recreate \
              springboot-app
        '''
            }
        }

        stage('Check Container Status') {
            steps {
                echo '8. Check container status'

                sh '''
            docker compose \
              -p "$PROJECT_NAME" \
              -f "$COMPOSE_FILE" \
              ps
        '''
            }
        }

        stage('Check Application Logs') {
            steps {
                echo '9. Check Spring Boot logs'

                sh '''
            sleep 10
            docker logs --tail 50 \
            springboot-mongodb-crud-container
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