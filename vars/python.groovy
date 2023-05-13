def linkChecks(COMPONENT) {
    sh '''
                   echo Lint Checks for $(COMPONENT)
                   echo installing jslint
                   # pylint *.py
                   echo performing lint checks for $(COMPONENT)
                   echo performing lint checks completed for $(COMPONENT)
       '''           
}

// Call is the default function which will be called when you call the filename
def call() {
    pipeline {
        agent any
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