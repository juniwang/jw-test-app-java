FROM tomcat:8.5
MAINTAINER Junbo Wang <juniwang@microsoft.com>

add /target/**/*.war /usr/local/tomcat/webapps/ROOT.war
