# ----- STAGE 1: Build ứng dụng bằng Maven -----
# Bắt đầu với image chứa sẵn Maven và JDK 11, đặt tên cho stage này là "builder"
FROM maven:3.8-jdk-11 AS builder

# Tạo một thư mục làm việc bên trong image
WORKDIR /app

# Copy file pom.xml trước để tận dụng cache của Docker
COPY pom.xml .

# Copy toàn bộ mã nguồn (thư mục src)
COPY src ./src

# Chạy lệnh Maven để build ra file .war, bỏ qua các bài test
RUN mvn clean package -DskipTests


# ----- STAGE 2: Tạo image cuối cùng với Tomcat -----
# Bắt đầu với image Tomcat 9 và JDK 11 quen thuộc
FROM tomcat:9.0-jdk11-temurin

# Xóa các ứng dụng web mặc định của Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Lệnh quan trọng: Copy file .war từ stage "builder" ở trên
# vào thư mục webapps của Tomcat và đổi tên thành ROOT.war
COPY --from=builder /app/target/BTC12.war /usr/local/tomcat/webapps/ROOT.war
