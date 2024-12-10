# Use OpenJDK base image
FROM openjdk:11-jre-slim

# Set working directory inside the container
WORKDIR /app

# Copy compiled Java code or JAR file to the container
COPY target/restaurant-management.jar /app/

# Copy MySQL connector (optional if needed)
COPY lib/mysql-connector.jar /app/lib/

# Set environment variables for database connectivity
ENV DB_HOST=mysql
ENV DB_PORT=3306
ENV DB_NAME=restaurant
ENV DB_USER=root
ENV DB_PASSWORD=helloworld

# Command to run the application
CMD ["java", "-cp", "/app:/app/lib/*", "MainClassName"]

# Expose the application port
EXPOSE 8080
