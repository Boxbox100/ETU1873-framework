export CLASSPATH=$CLASSPATH:.:/home/pri/apache-tomcat-10.0.27/lib/servlet-api.jar
mkdir /home/pri/temp/jar
javac -classpath $CLASSPATH:/home/pri/IdeaProjects/ETU1873-framework/Framework/src/main/classe -d /home/pri/IdeaProjects/ETU1873-framework/Framework/src/main/classe /home/pri/IdeaProjects/ETU1873-framework/Framework/src/main/java/ETU1873/framework/servlet/Singleton.java
javac -classpath $CLASSPATH:/home/pri/IdeaProjects/ETU1873-framework/Framework/src/main/classe -d /home/pri/IdeaProjects/ETU1873-framework/Framework/src/main/classe /home/pri/IdeaProjects/ETU1873-framework/Framework/src/main/java/ETU1873/framework/servlet/*.java
cd /home/pri/IdeaProjects/ETU1873-framework/Framework/src/main/classe
jar -cf ./framework.jar .
ls
cp -f /home/pri/IdeaProjects/ETU1873-framework/Framework/src/main/classe/framework.jar /home/pri/IdeaProjects/ETU1873-framework/testFramework/src/main/webapp/WEB-INF/lib

#
export CLASSPATH=$CLASSPATH:.:/home/pri/IdeaProjects/ETU1873-framework/testFramework/src/main/webapp/WEB-INF/lib/framework.jar
echo $CLASSPATH
javac -classpath $CLASSPATH:/home/pri/IdeaProjects/ETU1873-framework/testFramework/src/main/webapp/WEB-INF/classes -parameters -d /home/pri/IdeaProjects/ETU1873-framework/testFramework/src/main/webapp/WEB-INF/classes /home/pri/IdeaProjects/ETU1873-framework/testFramework/src/main/java/andrana/*.java
cd /home/pri/IdeaProjects/ETU1873-framework/testFramework/src/main/webapp
jar -cf ./testframework.war .
ls
cp -f ./testframework.war /home/pri/apache-tomcat-10.0.27/webapps

