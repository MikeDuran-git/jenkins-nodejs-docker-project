node {
   def commit_id
   stage('Preparation') {
     checkout scm
     sh "git rev-parse --short HEAD > .git/commit-id"                        
     commit_id = readFile('.git/commit-id').trim()
   }
   stage('test') {
     nodejs(nodeJSInstallationName: 'nodejs') {
       sh 'npm install'
       sh 'npm test'
     }
   }
   stage('docker build/push') {
     docker.withRegistry('https://index.docker.io/', '08c79c14-27da-4057-a252-a370fe1e507f') {
       def app = docker.build("durandocker3/jenkins_docker_repo:${commit_id}", '.').push()
     }
   }
}
