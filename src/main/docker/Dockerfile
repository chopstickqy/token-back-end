FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD github-token.war app.war
ENTRYPOINT["java", "-jar", "/app.war"]