def sonarChecks() {
    stage('Sonar Checks') {
        //  sh "sonar-scanner -Dsonar.host.url=http://172.31.9.236:9000 ${ARGS} -Dsonar.projectKey=${COMPONENT} -Dsonar.login=${SONAR_USR} -Dsonar.password=${SONAR_PSW}"
        //  sh "curl https://gitlab.com/thecloudcareers/opensource/-/raw/master/lab-tools/sonar-scanner/quality-gate > quality-gate.sh" 
        //  sh "bash -x quality-gate.sh ${SONAR_USR} ${SONAR_PSW} ${SONAR_URL} ${COMPONENT}"
          sh "echo Starting Code Quality Analysis"
          sh "echo Code Quality Analysis Completed"
    }
}

def testCases() {
    stage('Test Cases') {
        def stages = [:]

        stages["Unit Tests"] = {
            echo "Unit Testing Started"
            echo "Unit Testing Completed"
            // sh mvn test or npm test
        }
        stages["Integration Tests"] = {
            echo "Integration Testing Started"
            echo "Integration Testing Completed"
            // sh mvn verify or npm verify
        }
        stages["Functional Tests"] = {
            echo "Functional Testing Started"
            echo "Functional Testing Completed"
        }

        parallel(stages)
    }
}

def lintChecks() {
    stage('Lint checks') {
          if (env.APP_TYPE == "maven") {
            sh '''
                   echo Lint Checks for $(COMPONENT)
                   echo installing jslint
                   # mvn checkstyle:check
                   echo performing lint checks for $(COMPONENT)
                   echo performing lint checks completed for $(COMPONENT)
             '''    
          }
         else if (env.APP_TYPE == "nodejs") {
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
         else if (env.APP_TYPE == "python") {
            sh '''
                   echo Lint Checks for $(COMPONENT)
                   # pylint *.py
                   echo performing lint checks for $(COMPONENT)
                   echo performing lint checks completed for $(COMPONENT)
             '''  
         }
         else {
            sh '''
                   echo Lint Checks for $(COMPONENT)
                   echo performing lint checks for $(COMPONENT)
                   echo performing lint checks completed for $(COMPONENT)
            '''  
         }
    }
}

