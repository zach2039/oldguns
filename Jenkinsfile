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
                sh './gradlew clean --no-daemon'
            }
        }
        stage('Generate Data') {
            steps {
                echo 'Generating Data for Project'
                sh './gradlew runData --no-daemon'
            }
        }
        stage('Build and Deploy') {
            environment {
                add_git_rev = '1'
            }
            steps {
                echo 'Building Project'
                script {
                    sh './gradlew build --no-daemon'
                }
            }
        }
    }
    post {
        always {
            archive 'build/libs/**.jar'
			withCredentials([string(credentialsId: "oldguns-discord-webhook", variable: '$discordWebhookSecret')]) {
				discordSend description: "**Status:** " + currentBuild.currentResult.toLowerCase() + "\n**Branch:** ${BRANCH_NAME}\n**Build:** ${BUILD_NUMBER}\n**Changes:** ${RUN_CHANGES_DISPLAY_URL}\n",
					title: "Old Guns", 
					link: env.BUILD_URL,
					result: currentBuild.currentResult,
					webhookURL: "$discordWebhook"
			}
        }
    }
}
