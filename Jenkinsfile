pipeline {
  agent any
  stages {
    stage('Test') {
      steps {
        sh 'mvn clean verify'
      }
    }
    stage('Build') {
      steps {
        sh 'mvn install'
      }
    }
    stage('Artifacts') {
      steps {
        parallel(
                "Archive test results": {
                  junit 'target/failsafe-reports/*.xml'
                  junit 'target/surefire-reports/*.xml'

                  archiveArtifacts 'target/failsafe-reports/*.xml'
                  archiveArtifacts 'target/surefire-reports/*.xml'

                  fingerprint 'target/failsafe-reports/*.xml'
                  fingerprint 'target/surefire-reports/*.xml'
                },
                "Archive libs": {
                  archiveArtifacts 'target/*.jar'
                  fingerprint 'build/libs/*.jar'
                },
                "Archive pom": {
                  archiveArtifacts 'pom.xml'
                  fingerprint 'pom.xml'
                },
                "Archive Jenkinsfile": {
                  archiveArtifacts 'Jenkinsfile'
                  fingerprint 'Jenkinsfile'
                }
        )
      }
    }
  }
  tools {
    maven 'mvn3'
    jdk 'jdk8'
  }
  triggers {
    pollSCM('* * * * *')
  }
}