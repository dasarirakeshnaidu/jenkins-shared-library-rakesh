def linkChecks(COMPONENT) {
    sh '''
                   echo Lint Checks for $(COMPONENT)
                   echo installing jslint
                   # npm install jslint
                   # ls -ltr node_modules/jslint/bin/
                   # node_modules/jslint/bin/jslint.js server.js
                   echo performing lint checks
                   echo performing lint checks completed"
       '''           
}

// Call is the default function which will be called when you call the filename
def call() {
    pipeline {
        agents any
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