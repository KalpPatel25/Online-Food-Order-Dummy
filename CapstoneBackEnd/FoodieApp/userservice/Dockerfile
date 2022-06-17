FROM openjdk:11

WORKDIR usr/src

ADD ./target/userservice-0.0.1-SNAPSHOT.jar /usr/src/userservice-0.0.1-SNAPSHOT.jar
#
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_MY_URL=jdbc:mysql://localhost:3306/cgisample?createDatabaseIfNotExist=true&user=root&password=root

# jdbc:mysql://localhost:3306/foodieappuserdb?createDatabaseIfNotExist=true&user=root&password=K@lp2508

ENTRYPOINT ["java","-jar","userservice-0.0.1-SNAPSHOT.jar"]


