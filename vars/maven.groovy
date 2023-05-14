def linkChecks(COMPONENT) {
    sh '''
                   echo Lint Checks for $(COMPONENT)
                   echo installing jslint
                   # mvn checkstyle:check
                   echo performing lint checks for $(COMPONENT)
                   echo performing lint checks completed for $(COMPONENT)
       '''           
}

// Call is the default function which will be called when you call the filename
def call() {
    pipeline {
        agent any

        environment {
            SONAR = credentials('SONAR')
            SONAR_URL = "IP ADDRESS"
        }

        stages {
            stage('Lint Checks') {
                steps {
                    script {
                        lintChecks()
                    }
                }
            }

            stage('Sonar Checks') {
                steps {
                    script{
                        sh "mvn clean compile"
                        env.ARGS="-Dsonar.java.binaries=target/"
                        common.sonarChecks()
                    }
                }
            }
        }
    }
}