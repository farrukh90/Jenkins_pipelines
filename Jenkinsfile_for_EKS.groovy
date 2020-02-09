node{
    properties(
        [
        parameters([ 
        choice(choices: ['us-east-1', 'us-east-2', 'us-west-1', 'us-west-2'], description: 'Please provide a region', name: 'EKS_REGION'), 
        string(defaultValue: 'dev', description: 'Please enter an environment', name: 'ENVIR', trim: false),
        string(defaultValue: 'apply', description: 'Type your action e.g apply or destroy', name: 'ACTION', trim: false)])])
    
    
    
    stage("Pull Repo"){
        ws ("tmp/"){
            git 'https://github.com/farrukh90/terraform-iaac-eks-burak.git'
        }
    }
    stage("Download Terraform"){
        ws ("tmp/") {
            sh "terraform version"
            //sh "wget https://releases.hashicorp.com/terraform/0.12.19/terraform_0.12.19_linux_amd64.zip"
            //sh "unzip -o terraform_0.12.19_linux_amd64.zip"
            sh "./terraform version"
        }
    }
    stage("Set Backend"){
        ws ("tmp/"){
            sh "bash  setenv.sh configurations/${ENVIR}/${EKS_REGION}/${ENVIR}.tfvars"
            sh "./terraform init"
        }
    }
    stage("Plan"){
        ws ("tmp/") {
            sh "./terraform destroy  -var-file configurations/${ENVIR}/${EKS_REGION}/${ENVIR}.tfvars -auto-approve"
        }
    }
}