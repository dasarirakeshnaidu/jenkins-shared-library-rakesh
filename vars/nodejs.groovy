def linkChecks(COMPONENT) {
    sh '''
                   echo installing jslint
                   # npm install jslint
                   # ls -ltr node_modules/jslint/bin/
                   # node_modules/jslint/bin/jslint.js server.js
                   echo performing lint checks
                   echo performing lint checks completed"
       '''           
}

// Call is the default function which will be called when you call the filename
def call(COMPONENT) {
    pipeline {
        agents any
        stages {
            stage('Lint Checks') {
                steps {
                    script {
                        nodejs.lintChecks(COMPONENT)
                    }
                }
            }
        }
    }
}