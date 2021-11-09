FROM adoptopenjdk/openjdk11:x86_64-alpine-jdk-11.0.13_8-slim
COPY target/configuration-watcher-demo*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar","-Xmx=512M"]