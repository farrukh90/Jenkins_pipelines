node{
    stage("Pull Repo"){
        git 'https://github.com/farrukh90/terraform-iaac-eks-burak.git'
    }
    stage("stage1"){
        sh "terraform version"
    }
    stage("stage1"){
        echo "Hello"
    }
    stage("stage1"){
        echo "Hello"

    }
}