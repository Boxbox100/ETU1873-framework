export CLASSPATH=$CLASSPATH:.:/home/pri/apache-tomcat-10.0.27/lib/servlet-api.jar
javac -classpath $CLASSPATH:/home/pri/IdeaProjects/ETU1873-framework/Framework/src/main/classe -d /home/pri/IdeaProjects/ETU1873-framework/Framework/src/main/classe /home/pri/IdeaProjects/ETU1873-framework/Framework/src/main/java/ETU1873/framework/servlet/*.java
cd /home/pri/IdeaProjects/ETU1873-framework/Framework/src/main/classe
jar -cf ./framework.jar .
ls
cp -f /home/pri/IdeaProjects/ETU1873-framework/Framework/src/main/classe/framework.jar /home/pri/IdeaProjects/ETU1873-framework/testFramework/src/main/webapp/WEB-INF/lib

#
export CLASSPATH=$CLASSPATH:.:/home/pri/IdeaProjects/ETU1873-framework/testFramework/src/main/webapp/WEB-INF/lib/framework.jar
javac -classpath $CLASSPATH:/home/pri/IdeaProjects/ETU1873-framework/testFramework/src/main/webapp/WEB-INF/classes -d /home/pri/IdeaProjects/ETU1873-framework/testFramework/src/main/webapp/WEB-INF//classes /home/pri/IdeaProjects/ETU1873-framework/testFramework/src/main/java/test/*.java
cd /home/pri/IdeaProjects/ETU1873-framework/testFramework/src/main/webapp
jar -cf ./testframework.war ./WEB-INF
cp /testframework.war /home/pri/apache-tomcat-10.0.27/webapps