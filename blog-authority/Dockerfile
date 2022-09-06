FROM openjdk:11
ADD /target/blog-authority-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Xms64m","-Xmx256m","-jar","app.jar"]