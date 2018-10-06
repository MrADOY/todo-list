node {
    stage ("Clone the project"){
      git 'https://github.com/MrADOY/todo-list.git'
    }
    stage("Compilation and Analysis") {
      sh "./mvnw clean install -DskipTests"
    }
    stage("Runing unit tests") {
      sh "./mvnw test -Punit"
    }
      stage("Staging") {
      sh 'curl -X POST http://localhost:8989/actuator/shutdown || true'
        withEnv(['JENKINS_NODE_COOKIE=dontkill']) {
          sh 'nohup ./mvnw spring-boot:run -Dserver.port=8989 &'
        }
      }
}
