node{
    stage("Pull Repo"){
        git 'https://github.com/farrukh90/terraform-iaac-eks-burak.git'
    }
    stage("stage1"){
        sh "terraform version"
        sh "wget https://releases.hashicorp.com/terraform/0.12.19/terraform_0.12.19_linux_amd64.zip"
        sh "unzip terraform_0.12.19_linux_amd64.zip"
        sh "./terraform version"
    }
    stage("stage1"){
        echo "Hello"
    }
    stage("stage1"){
        echo "Hello"

    }
}