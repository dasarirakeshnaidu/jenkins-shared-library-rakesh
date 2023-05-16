// Scripted pipeline

def call {
    node {
        env.APP_TYPE="maven"
        common.lintchecks()
        env.ARGS="-Dsonar.java.binaries=target/"
        common.sonarChecks()
        common.testCases()
    }
}

// Call is the default function which will be called when you call the filename
// **** Uncomment it only when you prefer to use Declarative Pipeline
// def call() {
//     pipeline {
//         agent any

//         environment {
//             SONAR = credentials('SONAR')
//             SONAR_URL = "IP ADDRESS"
//         }

//         stages {
//             stage('Lint Checks') {
//                 steps {
//                     script {
//                         lintChecks()
//                     }
//                 }
//             }

//             stage('Sonar Checks') {
//                 steps {
//                     script{
//                         sh "mvn clean compile"
//                         env.ARGS="-Dsonar.java.binaries=target/"
//                         common.sonarChecks()
//                     }
//                 }
//             }

//             stage('Test Cases') {
//             parallel {
//                 stage('Unit Testing') {
//                     steps {
//                       // sh "mvn test"
//                       sh "echo performing Unit Testing"
//                     }
//                 }
//                stage('Integration Testing') {
//                     steps {
//                       // sh "mvn verify"  
//                       sh "echo performing Integration Testing"
//                     }
//                 }
//                stage( 'Functional Testing') {
//                     steps {
//                       sh "echo performing Functional Testing"
//                     }
//                 }
//             }
//         }
//     }
// }