#!/usr/bin/env groovy

pipeline {
    agent any
    tools {
        jdk "openjdk-17"
    }
    stages {
        stage('Clean') {
            steps {
                echo 'Cleaning Project'
                sh 'chmod +x gradlew'
                sh './gradlew clean'
            }
        }
        stage('Build and Deploy') {
            environment {
                add_git_rev = '1'
            }
            steps {
                echo 'Building Project'
                script {
                    sh './gradlew build'
                }
            }
        }
    }
    post {
        always {
            archive 'build/libs/**.jar'
        }
    }
}