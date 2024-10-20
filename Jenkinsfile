
pipeline {
    agent any
    tools{
        maven 'maven'
        jdk 'JDK17'  
    }

    stages{
        stage('Install Docker') {
            steps {
                sh '''
                    curl -fsSL https://get.docker.com -o get-docker.sh
                    sh get-docker.sh
                    usermod -aG docker jenkins
                '''
            }
        }

        stage('Build Maven'){
            steps{
                checkout scmGit(branches: [[name: '*/main']], 
                extensions: [], 
                userRemoteConfigs: [[url: 'https://github.com/amponsemmichael/shop-now']])
                sh 'mvn clean install'
            }
        }
        
        stage('Build docker image'){
            steps{
                script{
                    //sh 'docker build -t devops-integration .'
                    echo 'building docker image...'
                }
            }
        }
        stage('Push image to Hub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                   sh 'docker login -u amponsem17 -p ${dockerhubpwd}'

}
                  // sh 'docker push devops-integration'
                    echo 'pushing image to docker hub...'
                }
            }
        }
        // stage('Deploy to k8s'){
        //     steps{
        //         script{
        //             kubernetesDeploy (configs: 'deploymentservice.yaml',kubeconfigId: 'k8sconfigpwd')
        //         }
        //     }
        // }
    }
}
