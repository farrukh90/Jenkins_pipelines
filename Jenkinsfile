pipeline {
  agent any
  stages {
    stage('Stage1') {
      parallel {
        stage('Stage1') {
          steps {
            echo 'Hello'
          }
        }

        stage('Test') {
          steps {
            sh 'echo "Hello"'
          }
        }

      }
    }

    stage('Stage2') {
      steps {
        echo 'stage2'
      }
    }

    stage('Stage3') {
      steps {
        echo 'step3'
      }
    }

  }
}