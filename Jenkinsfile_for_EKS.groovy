node{
    properties(
        [
        parameters([ 
        choice(choices: ['dev', 'qa', 'stage', 'prod'], description: 'Please choose an environment', name: 'ENVIR'),
        string(defaultValue: 'apply', description: 'Type your action e.g apply or destroy', name: 'ACTION', trim: false),
        ])])
    stage("Pull Repo"){
        ws ("tmp/"){
            git 'https://github.com/farrukh90/terraform-iaac-eks-burak.git'
        }
    }
    stage("Check Terraform"){
        ws ("tmp/") {
            def exists = fileExists './terraform'
            if (exists) {
                echo 'terraform exists'
            } else {
                sh "wget https://releases.hashicorp.com/terraform/0.12.19/terraform_0.12.19_linux_amd64.zip"
                sh "unzip -o terraform_0.12.19_linux_amd64.zip"
                sh "./terraform version"
            }
        }
    }
    stage("Download kubectl"){
        ws("tmp/"){
            def exists = fileExists '/tmp/kubectl'
            if (exists) {
                echo 'kubectl exists'
            } else {
                sh "curl -LO https://storage.googleapis.com/kubernetes-release/release/v1.15.0/bin/linux/amd64/kubectl"
                sh "chmod +x kubectl"
                sh "cp -f kubectl /tmp"
            }
        }
    }
    stage("Set Backend"){
        ws ("tmp/"){
            sh "bash  setenv.sh configurations/${ENVIR}/${ENVIR}.tfvars"
            sh "./terraform init"
        }
    }
    stage("Plan"){
        ws ("tmp/") {
            sh "./terraform ${ACTION}  -var-file configurations/${ENVIR}/${ENVIR}.tfvars -auto-approve"
        }
    }
}