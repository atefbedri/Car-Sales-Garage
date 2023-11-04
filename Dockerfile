FROM adoptopenjdk/openjdk11
ARG JAR_FILE=target/carsales-*.war
COPY ${JAR_FILE} app.war
ENTRYPOINT ["java","-jar","app.war", "-Xmx5G"]