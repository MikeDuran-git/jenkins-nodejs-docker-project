job('NodeJS Docker example') {
    scm {
        git('https://github.com/MikeDuran-git/jenkins-nodejs-docker-project') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('mike.duran@hotmail.de')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
	shell("npm install")
        dockerBuildAndPublish {
            repositoryName('durandocker3/jenkins_docker_repo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('durandocker3')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
