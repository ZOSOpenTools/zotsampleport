// 
// To be deleted after we move to new cicd-xxx.groovy
//

node('linux') 
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/ZOSOpenTools/zotsampleport.git']]])
    }

    stage('Build') {
      build job: 'Port-Pipeline', parameters: [
        string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/ZOSOpenTools/zotsampleport.git'), 
        string(name: 'PORT_DESCRIPTION', value: 'A sample z/OS Open Source project' )
      ]
    }
}
