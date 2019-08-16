pipeline {
  agent none
  stages {
    stage('Build') {
      agent {
        docker {
          image: 'maven:3-alpine'
        }
      }
      steps {
        sh 'mvn clean install' 
      }
    }
  }
}
