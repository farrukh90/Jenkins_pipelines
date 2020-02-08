node {
    stage("Pull Repo"){
        echo "hello"
    }
    stage("Build Image"){
        sh "packer version"
    }
    stage("Send Notification to Slack"){
        echo "Hello"
        
    }
    stage("Send Email"){
        echo "Hello"
        
    }
}