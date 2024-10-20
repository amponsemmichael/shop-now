pipeline {
    agent any
    tools {
        maven 'maven'
        jdk 'JDK17'  
    }
    environment {
        DOCKER_HOST = 'tcp://localhost:2375'
    }
    stages {
        stage('Check Docker Desktop') {
            steps {
                script {
                    try {
                        sh 'docker info'
                        sh 'docker --version'
                        sh 'docker-compose --version'
                        echo "Docker Desktop is running properly"
                    } catch (Exception e) {
                        error "Docker Desktop check failed: ${e.message}"
                    }
                }
            }
        }
        
        stage('Build Maven') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], 
                extensions: [], 
                userRemoteConfigs: [[url: 'https://github.com/amponsemmichael/shop-now']])
                
                script {
                    try {
                        sh 'mvn clean install'
                    } catch (Exception e) {
                        error "Maven build failed: ${e.message}"
                    }
                }
            }
        }
        
        stage('Build docker image') {
            steps {
                script {
                    try {
                        sh 'ls -la'  // List files to verify Dockerfile exists
                        sh 'docker build -t amponsem17/devops-integration .'
                    } catch (Exception e) {
                        error "Docker build failed: ${e.message}"
                    }
                }
            }
        }
        
        stage('Push image to Hub') {
            steps {
                script {
                    try {
                        withCredentials([usernamePassword(
                            credentialsId: 'dockerhub-credentials', 
                            passwordVariable: 'DOCKER_PASSWORD', 
                            usernameVariable: 'DOCKER_USERNAME'
                        )]) {
                            sh "echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin"
                            sh 'docker push amponsem17/devops-integration'
                        }
                    } catch (Exception e) {
                        error "Docker push failed: ${e.message}"
                    }
                }
            }
        }
    }
    
    post {
        always {
            sh 'docker logout'
            cleanWs()
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed! Check the logs for details.'
        }
    }
}





// pipeline {
//     agent any
//     tools {
//         maven 'maven'
//         jdk 'JDK17'  
//     }
//     stages {
//         stage('Check Docker Desktop') {
//             steps {
//                 script {
//                     try {
//                         if (isUnix()) {
//                             // Check if Docker Desktop is running on Unix
//                             def dockerInfo = sh(script: 'docker info', returnStatus: true)
//                             if (dockerInfo != 0) {
//                                 error "Docker Desktop is not running. Please start Docker Desktop."
//                             }
//                             sh 'docker --version'
//                             sh 'docker-compose --version'
//                         } else {
//                             // Check if Docker Desktop is running on Windows
//                             def dockerInfo = bat(script: 'docker info', returnStatus: true)
//                             if (dockerInfo != 0) {
//                                 error "Docker Desktop is not running. Please start Docker Desktop."
//                             }
//                             bat 'docker --version'
//                             bat 'docker-compose --version'
//                         }
//                         echo "Docker Desktop is running properly"
//                     } catch (Exception e) {
//                         error "Docker Desktop check failed: ${e.message}"
//                     }
//                 }
//             }
//         }
        
//         stage('Build Maven') {
//             steps {
//                 checkout scmGit(branches: [[name: '*/main']], 
//                 extensions: [], 
//                 userRemoteConfigs: [[url: 'https://github.com/amponsemmichael/shop-now']])
                
//                 script {
//                     try {
//                         if (isUnix()) {
//                             sh 'mvn clean install'
//                         } else {
//                             bat 'mvn clean install'
//                         }
//                     } catch (Exception e) {
//                         error "Maven build failed: ${e.message}"
//                     }
//                 }
//             }
//         }
        
//         stage('Build docker image') {
//             steps {
//                 script {
//                     try {
//                         if (isUnix()) {
//                             // Add build context check
//                             sh 'ls -la'  // List files to verify Dockerfile exists
//                             sh 'docker build -t amponsem17/devops-integration .'
//                         } else {
//                             bat 'dir'  // List files to verify Dockerfile exists
//                             bat 'docker build -t amponsem17/devops-integration .'
//                         }
//                     } catch (Exception e) {
//                         error "Docker build failed: ${e.message}"
//                     }
//                 }
//             }
//         }
        
//         stage('Push image to Hub') {
//             steps {
//                 script {
//                     try {
//                         withCredentials([usernamePassword(
//                             credentialsId: 'dockerhub-credentials', 
//                             passwordVariable: 'DOCKER_PASSWORD', 
//                             usernameVariable: 'DOCKER_USERNAME'
//                         )]) {
//                             if (isUnix()) {
//                                 sh "echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin"
//                                 sh 'docker push amponsem17/devops-integration'
//                             } else {
//                                 bat "echo %DOCKER_PASSWORD% | docker login -u %DOCKER_USERNAME% --password-stdin"
//                                 bat 'docker push amponsem17/devops-integration'
//                             }
//                         }
//                     } catch (Exception e) {
//                         error "Docker push failed: ${e.message}"
//                     }
//                 }
//             }
//         }
//     }
    
//     post {
//         always {
//             script {
//                 // Cleanup Docker
//                 if (isUnix()) {
//                     sh 'docker logout'
//                     // Optional: Remove the built image
//                     // sh 'docker rmi amponsem17/devops-integration'
//                 } else {
//                     bat 'docker logout'
//                     // Optional: Remove the built image
//                     // bat 'docker rmi amponsem17/devops-integration'
//                 }
//             }
//             cleanWs()
//         }
//         success {
//             echo 'Pipeline completed successfully!'
//         }
//         failure {
//             echo 'Pipeline failed! Check the logs for details.'
//         }
//     }
// }


// pipeline {
//     agent any
//     tools {
//         maven 'maven'
//         jdk 'JDK17'  
//     }
//     stages {
//         stage('Check Docker') {
//             steps {
                
//     }
//  }
        
//         stage('Build Maven') {
//             steps {
//                 checkout scmGit(branches: [[name: '*/main']], 
//                 extensions: [], 
//                 userRemoteConfigs: [[url: 'https://github.com/amponsemmichael/shop-now']])
//                 sh 'mvn clean install'
//             }
//         }
        
//         stage('Build docker image') {
//             steps {
//                 script {
//                     bat 'docker build -t amponsem17/devops-integration .'
//                 }
//             }
//         }
        
//         stage('Push image to Hub') {
//             steps {
//                 script {
//                     withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
//                         bat 'docker login -u amponsem17 -p %dockerhubpwd%'
//                     }
//                     bat 'docker push amponsem17/devops-integration'
//                 }
//             }
//         }
//     }
// }

// pipeline {
//     agent any
//     tools{
//         maven 'maven'
//         jdk 'JDK17'  
//     }

//     stages{
//         stage('Install Docker') {
//             steps {
//                 sh '''
//                     curl -fsSL https://get.docker.com -o get-docker.sh
//                     sh get-docker.sh
//                     usermod -aG docker jenkins
//                 '''
//             }
//         }

//         stage('Build Maven'){
//             steps{
//                 checkout scmGit(branches: [[name: '*/main']], 
//                 extensions: [], 
//                 userRemoteConfigs: [[url: 'https://github.com/amponsemmichael/shop-now']])
//                 sh 'mvn clean install'
//             }
//         }
        
//         stage('Build docker image'){
//             steps{
//                 script{
//                     sh 'docker build -t devops-integration .'
//                 }
//             }
//         }
//         stage('Push image to Hub'){
//             steps{
//                 script{
//                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
//                    sh 'docker login -u amponsem17 -p ${dockerhubpwd}'

// }
//                    sh 'docker push devops-integration'
//                 }
//             }
//         }
        // stage('Deploy to k8s'){
        //     steps{
        //         script{
        //             kubernetesDeploy (configs: 'deploymentservice.yaml',kubeconfigId: 'k8sconfigpwd')
        //         }
        //     }
        // }
    //}
//}
