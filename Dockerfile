# Use Maven image to build the application
FROM maven:3.9-openjdk-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use OpenJDK for runtime
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built jar from build stage
COPY --from=build /app/target/TouristeProject-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
