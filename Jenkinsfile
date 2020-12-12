#!/usr/bin/env groovy
// see https://jenkins.io/doc/book/pipeline/syntax/

pipeline {

    agent any

    tools {
        maven "Maven"
    }

    options {
        timestamps()
        ansiColor("xterm")
    }

    stages {

        stage("Build & install SNAPSHOT locally") {
            steps {
                sh "mvn -B clean install"
            }
        }

    }

    post {
        always {
            deleteDir()
        }
    }
}