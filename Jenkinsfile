

node {
    env.JAVA_HOME="${tool 'openjdk-11'}"
    env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
    git url: 'https://github.com/arturgaluszka/Chirper.git'
    def mvnHome = tool 'maven3'
    sh "${mvnHome}/bin/mvn -B verify"
}
