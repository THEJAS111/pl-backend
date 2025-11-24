# ------------------------------------
# STAGE 1: BUILDER STAGE (Compiles the application into a JAR)
# ------------------------------------
# Uses a full JDK 21 image for compiling and packaging the JAR
FROM eclipse-temurin:21-jdk-jammy AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the build configuration file first (Leverages Docker caching)
COPY pom.xml .

# Copy the source code and necessary scripts
COPY src ./src
COPY mvnw .
COPY .mvn ./.mvn

# Run the Maven Wrapper command to compile and package the application
# This creates the executable JAR file in the /app/target/ directory
RUN ./mvnw clean package -DskipTests

# ------------------------------------
# STAGE 2: RUNTIME STAGE (Runs the JAR in a minimal JRE)
# ------------------------------------
# Uses a JRE-only image for the final, minimal, and secure container
FROM eclipse-temurin:21-jre-jammy

# Expose the default Spring Boot port
EXPOSE 8080

# Define the JAR file location from the build stage
# Note: Adjust the filename if your final JAR name is different from target/*.jar
ARG JAR_FILE=target/*.jar

# Copy the generated JAR file from the 'builder' stage into the final image's root
COPY --from=builder /app/${JAR_FILE} app.jar

# Define the command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "/app.jar"]

