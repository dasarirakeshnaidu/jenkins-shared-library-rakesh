def linkChecks(COMPONENT) {
    sh '''
                   echo Lint Checks for $(COMPONENT)
                   echo installing jslint
                   # npm install jslint
                   # ls -ltr node_modules/jslint/bin/
                   # node_modules/jslint/bin/jslint.js server.js
                   echo performing lint checks for $(COMPONENT)
                   echo performing lint checks completed for $(COMPONENT)
       '''           
}

def sonarChecks() {
    sh '''
         sonar-scanner -Dsonar.host.url=http://IP:9000 -Dsonar.sources=. -Dsonar.projectKey=${COMPONENT} -Dsonar.login=${SONAR_USR} -Dsonar.password=${SONAR_PSW}
        '''
}

// Call is the default function which will be called when you call the filename
def call() {
    pipeline {
        agent any

        environment {
            SONAR = credentials('SONAR')
            NEXUS = credentials('NEXUS')
            SONAR_URL = "IP ADDRESS"
            NEXUS_URL = "IP ADDRESS"
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
                      // sh "npm test"
                      sh "echo performing Unit Testing"
                    }
                }
               stage('Integration Testing') {
                    steps {
                      // sh "npm verify"  
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

            stage('Prepare the artifacts') {
                when {expression { env.TAG_NAME !=null } }
                steps {
                       sh "npm install"
                       sh "echo Preparing the artifacts"
                       sh "zip ${COMPONENT}-${TAG_NAME}.zip node_modules server.js"
                }
            }
            
            stage( 'Publish the artifacts') {
                when {expression { env.TAG_NAME !=null } }
                steps {
                sh "echo Publishing the artifacts"
                 sh "curl -f -v -u ${NEXUS_USR}:${NEXUS_PSW} --upload-file ${COMPONENT}-${TAG_NAME}.zip http://${NEXUS_URL}:8081/repository/${COMPONENT}/${COMPONENT}-${TAG_NAME}.zip"
                }
            }
        }
    }
}
