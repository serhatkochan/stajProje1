FROM openjdk:17
WORKDIR /app
ADD target/stajProje1-0.0.1-SNAPSHOT.jar stajProje1-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "stajProje1-0.0.1-SNAPSHOT.jar"]

#FROM openjdk:17
#WORKDIR /app
#COPY . .
#RUN chmod +x mvnw && ./mvnw clean install -U
#ENTRYPOINT ["./mvnw", "spring-boot:run"]