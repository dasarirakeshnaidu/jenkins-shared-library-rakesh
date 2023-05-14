def linkChecks(COMPONENT) {
    sh '''
                   echo Lint Checks for $(COMPONENT)
                   # pylint *.py
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
                        env.ARGS="-Dsonar.sources=."
                        common.sonarChecks()
                    }
                }
            }

            stage('Test Cases') {
            parallel {
                stage('Unit Testing') {
                    steps {
                      // sh "py test"
                      sh "echo performing Unit Testing"
                    }
                }
               stage('Integration Testing') {
                    steps {
                      // sh "py verify"  
                      sh "echo performing Integration Testing"
                    }
                }
               stage( 'Functional Testing') {
                    steps {
                      sh "echo performing Functional Testing"
                    }
        }
    }
}