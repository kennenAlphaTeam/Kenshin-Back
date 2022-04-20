FROM adoptopenjdk/openjdk11

COPY local-ssh-keystore.p12 local-ssh-keystore.p12
COPY build/libs/kenshin-0.1.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["--spring.profiles.active=local,dev"]
EXPOSE 8080