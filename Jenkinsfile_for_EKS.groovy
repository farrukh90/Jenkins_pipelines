node{
    properties(
        [
        parameters([ 
        choice(choices: ['us-east-1', 'us-east-2', 'us-west-1', 'us-west-2'], description: 'Please provide a region', name: 'EKS_REGION'), 
        choice(choices: ['t2.micro', 'm4.large', 'm5.large'], description: 'Please choose instance type', name: 'INSTANCE_TYPE')])])
    
    stage("Pull Repo"){
        ws ("tmp/"){
            git 'https://github.com/farrukh90/terraform-iaac-eks-burak.git'
        }
    }
    stage("Download Terraform"){
        ws ("tmp/") {
            sh "terraform version"
            sh "wget https://releases.hashicorp.com/terraform/0.12.19/terraform_0.12.19_linux_amd64.zip"
            sh "unzip -o terraform_0.12.19_linux_amd64.zip"
            sh "./terraform version"
        }
    }
    stage("Set Backend"){
        ws ("tmp/"){
            sh "bash source setenv.sh configurations/dev/us-west-2/dev.tfvars"
            //sh "./terraform init"
        }
    }
    stage("Plan"){
        ws ("tmp/") {
            sh "./terraform plan -var-file configurations/dev/us-west-2/dev.tfvars"
        }
    }
}