pipeline {
    agent any

    tools {
        jdk 'java'   // must match JDK name in Manage Jenkins → Tools
    }

    stages {

        stage('Checkout Source Code') {
            steps {
                // Jenkins automatically checks out the branch where Jenkinsfile exists
                echo "Source code checked out successfully"
            }
        }

        stage('Verify Java') {
            steps {
                sh '''
                    echo "JAVA_HOME=$JAVA_HOME"
                    java -version
                '''
            }
        }

        stage('Build') {
            steps {
                echo "Build stage goes here"
                // Example (uncomment if using Maven):
                // sh 'mvn clean package'
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline executed successfully'
        }
        failure {
            echo '❌ Pipeline failed'
        }
    }
}
