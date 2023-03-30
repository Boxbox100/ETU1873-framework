javac -classpath $CLASSPATH:/home/pri/IdeaProjects/ETU1873-framework/Framework/src/main/classe:/home/pri/apache-tomcat-10.0.27/lib/servlet-api.jar -d /home/pri/IdeaProjects/ETU1873-framework/Framework/src/main/classe /home/pri/IdeaProjects/ETU1873-framework/Framework/src/main/java/ETU1873/framework/servlet/*.java




jar -cf /home/pri/IdeaProjects/ETU1873-framework/Framework/src/main/jar/framework.jar /home/pri/IdeaProjects/ETU1873-framework/Framework/src/main/classe


cp /home/pri/IdeaProjects/ETU1873-framework/Framework/src/main/jar/framework.jar /home/pri/IdeaProjects/ETU1873-framework/testFramework/src/main/webapp/WEB-INF/lib


javac -classpath $CLASSPATH:/home/pri/IdeaProjects/ETU1873-framework/testFramework/src/main/classes:/home/pri/apache-tomcat-10.0.27/lib/servlet-api.jar -d /home/pri/IdeaProjects/ETU1873-framework/testFramework/src/main/classes /home/pri/IdeaProjects/ETU1873-framework/testFramework/src/main/java/test/*.java



cd /home/pri/IdeaProjects/ETU1873-framework/testFramework/src/main/webapp
cp -R /home/pri/IdeaProjects/ETU1873-framework/testFramework/src/main/webapp/WEB-INF/classes ./WEB-INF
pwd
ls
jar -cf ./frame.jar ./WEB-INF

cp ./frame.jar /home/pri/apache-tomcat-10.0.27/webapps
