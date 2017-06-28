FROM tomcat:8.5
MAINTAINER Junbo Wang <juniwang@microsoft.com>

ADD /target/jw-java-helloworld.war /usr/local/tomcat/webapps/ROOT.war
RUN \
  rm -rf /usr/local/tomcat/webapps/ROOT \
  && echo "root:Docker!" | chpasswd \
  && apt-get update \
  && apt-get install -y --no-install-recommends openssh-server \
  && chmod 755 /bin/init_container.sh 
