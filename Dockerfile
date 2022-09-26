FROM openjdk:18
COPY ./out/production/Containerisation/ /tmp
WORKDIR /tmp
EXPOSE 3308
ENTRYPOINT ["java","services.Main"]