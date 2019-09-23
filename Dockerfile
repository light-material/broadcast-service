FROM maven:3.6-jdk-11

COPY pom.xml /usr/src/mercury/

RUN cd /usr/src/mercury/ \
    && mvn dependency:go-offline

COPY src/ usr/src/mercury/src/

RUN cd /usr/src/mercury/ \
    && mvn clean package -Dmaven.test.skip

EXPOSE 8080

CMD ["java","-jar","/usr/src/mercury/target/broadcast-service-0.0.1-SNAPSHOT.jar"]
