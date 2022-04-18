From openjdk:8
copy ./target/hotel-service-0.0.1-SNAPSHOT.jar hotel-service-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","hotel-service-0.0.1-SNAPSHOT.jar"]


