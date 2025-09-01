pipeline {
    agent any
    stages {
        stage("Build & Package") {
            steps {
                sh "mvn clean package"
            }
        }
        stage("Build Docker Image") {
            steps {
                sh "docker build -t trainbook-app ."
            }
        }
        stage("Deploy") {
            steps {
                sh "docker rm -f trainbook-container || true"
                sh "docker run -d --name trainbook-container -p 8082:8080 trainbook-app"
            }
        }
    }
}
