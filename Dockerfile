from tomcat:9
copy target/*.war /usr/local/tomcat/webapps/train.war
cmd ["catalina.sh", "run"]