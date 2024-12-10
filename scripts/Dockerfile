FROM mysql:8.0

# Environment variables for database
ENV MYSQL_ROOT_PASSWORD=helloworld
ENV MYSQL_DATABASE=restaurant

# Copy SQL initialization scripts
COPY ./*.sql /docker-entrypoint-initdb.d/

# Expose MySQL port
EXPOSE 3307
