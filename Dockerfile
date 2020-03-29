FROM gradle:6.3-jdk8 as builder

COPY . /usr/src/app
USER root
RUN chown -R gradle:gradle /usr/src/app
USER gradle

WORKDIR /usr/src/app
RUN gradle assemble

FROM openjdk:8-jre-alpine
WORKDIR /root/
COPY --from=builder /usr/src/app/build/libs/hubject-destination-charging-0.0.1-SNAPSHOT.jar .

EXPOSE 8090
CMD ["java", "-jar", "./hubject-destination-charging-0.0.1-SNAPSHOT.jar"]