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
        parallel(
                "Build": { sh 'mvn install -DskipTests' },
                "Archive surefire reports": {
                  junit 'target/surefire-reports/*.xml'

                  archiveArtifacts 'target/surefire-reports/*.xml'
                  archiveArtifacts 'target/surefire-reports/*.txt'

                  fingerprint 'target/surefire-reports/*.xml'
                }
        )
      }
    }
    stage('Artifacts') {
      steps {
        parallel(
                "Archive jar": {
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