FROM java:8-jre-alpine
MAINTAINER Ihor Lavryniuk <sp.titan@gmail.com>

ADD target/logs.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar