FROM gradle:6.3-jdk8 as builder

COPY . /usr/src/destination_charging
USER root
RUN chown -R gradle:gradle /usr/src/destination_charging
USER gradle

WORKDIR /usr/src/destination_charging
RUN gradle assemble

FROM openjdk:8-jre-alpine
WORKDIR /root/
COPY --from=builder /usr/src/destination_charging/build/libs/hubject-destination-charging-0.0.1-SNAPSHOT.jar .

EXPOSE 8090
CMD ["java", "-jar", "./hubject-destination-charging-0.0.1-SNAPSHOT.jar"]