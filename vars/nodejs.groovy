@Library('rakesh-shared-library') _

pipeline {
      agent any 
      stages { 

        stage( 'Performing lint checks') {
            steps {
                script {
                    sample.info('SharedLibrary', 'stage.google.com')
                }
           }
       }

        stage ('Downloading the dependencies') {
            steps {
                sh "npm install"
           }
        }
    }
}