# Build stage
FROM maven:3.9-eclipse-temurin-11 AS builder

WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:11-jre-alpine

WORKDIR /app

# Copy the built JAR from builder stage
COPY --from=builder /app/target/calculator-app-*.jar app.jar

# Create a non-root user for security (Alpine Linux compatible)
RUN addgroup -g 1001 calculator && adduser -D -u 1001 -G calculator calculator && chown -R calculator:calculator /app
USER calculator

# Expose port for web application
EXPOSE 8080

# Health check using wget (available in Alpine)
HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
    CMD wget -q -O- http://localhost:8080/api/calculator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]