# Base image
FROM openjdk:11-jdk-slim

# Set timezone
ENV TZ=Asia/Seoul

# Install MySQL 5.7
RUN apt-get update && \
    apt-get install -y mysql-server=5.7.* && \
    rm -rf /var/lib/apt/lists/* && \
    service mysql start && \
    mysql -e "ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';"

# Set MySQL configuration
COPY my.cnf /etc/mysql/conf.d/my.cnf

# Install Spring Boot app
COPY app.jar app.jar

# Expose ports
EXPOSE 8080 3306

# Start Spring Boot app
CMD ["java", "-jar", "app.jar"]
