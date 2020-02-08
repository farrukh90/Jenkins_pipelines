node{
    properties([parameters([choice(choices: ['dev', 'qa', 'stage', 'prod'], description: 'Please provide environment', name: 'ENVIR'), choice(choices: ['us-east-1', 'us-east-2', 'us-west-1', 'us-west-2'], description: 'Please provide a region', name: 'EKS_REGION'), string(defaultValue: 'dev-test', description: 'Please provide a cluster name e.g  dev', name: 'CLUSTER_NAME', trim: false), choice(choices: ['t2.micro', 'm4.large', 'm5.large'], description: 'Please choose instance type', name: 'INSTANCE_TYPE'), choice(choices: ['48', '96'], description: 'Please provide max limit for ASG', name: 'ASG_MAX'), choice(choices: ['1', '3'], description: 'Please provide min limit for ASG', name: 'ASG_MIN'), choice(choices: ['1.14', '1.15', '1.16', '1.17'], description: 'Please provide EKS version', name: 'CLUSTER_VERSION')])])
    
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
            sh "./terraform version"}
    }
    stage("Set Backend"){
        ws ("tmp/"){
            sh "./terraform init"
        }
    }
    stage("Plan"){
        ws ("tmp/") {
            sh "./terraform plan -var-file configurations/dev/us-west-2/dev.tfvars"
        }
    }
}