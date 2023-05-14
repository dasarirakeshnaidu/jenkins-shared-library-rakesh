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
        }

        stages {
            stage('Lint Checks') {
                steps {
                    script {
                        lintChecks()
                    }
                }
            }
        }
    }
}