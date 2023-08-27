FROM eclipse-temurin:17-alpine as build
EXPOSE 8080
COPY target/*.jar webfluxdemo.jar
ENTRYPOINT ["java","-jar","/webfluxdemo.jar"]