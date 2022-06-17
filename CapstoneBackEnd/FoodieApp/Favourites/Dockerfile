FROM openjdk:11

WORKDIR usr/src

ADD ./target/favourites-0.0.1-SNAPSHOT.jar /usr/src/favourites-0.0.1-SNAPSHOT.jar

ENV MONGO_URL=mongodb://localhost:27017/dockmongodb

ENTRYPOINT ["java","-jar","favourites-0.0.1-SNAPSHOT.jar"]
