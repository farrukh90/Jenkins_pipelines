node{
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
            pwd()
            sh "ls -l ${WORKSPACE}"
            sh "ls -l ../"
            sh "ls "
            sh "./${WORKSPACE}/tmp/source setenv.sh configurations/dev/us-west-2/dev.tfvars"
        }
    }
    stage("stage1"){
        echo "Hello"

    }
}