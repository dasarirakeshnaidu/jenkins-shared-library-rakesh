// Scripted pipeline

def call {
    node {
        env.APP_TYPE="python"
        common.lintchecks()
        env.ARGS="-Dsonar.sources=."
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
//                         env.ARGS="-Dsonar.sources=."
//                         common.sonarChecks()
//                     }
//                 }
//             }

//             stage('Test Cases') {
//             parallel {
//                 stage('Unit Testing') {
//                     steps {
//                       // sh "py test"
//                       sh "echo performing Unit Testing"
//                     }
//                 }
//                stage('Integration Testing') {
//                     steps {
//                       // sh "py verify"  
//                       sh "echo performing Integration Testing"
//                     }
//                 }
//                stage( 'Functional Testing') {
//                     steps {
//                       sh "echo performing Functional Testing"
//                     }
//         }
//     }
// }