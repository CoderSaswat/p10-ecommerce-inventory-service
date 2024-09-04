# Stage 1: Build the application
FROM maven:3.9.5-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY pom.xml /app/
COPY src /app/src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:17.0.5_8-jre

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the build stage into the runtime container
COPY --from=build /app/target/*.jar /app/app.jar

# Expose the port the application will run on
EXPOSE 8080

# Specify the command to run on container start
CMD ["java", "-jar", "app.jar"]