FROM tomcat:8.5
MAINTAINER Junbo Wang <juniwang@microsoft.com>

add /target/jw-java-helloworld.war /usr/local/tomcat/webapps/ROOT.war
