# Bắt đầu từ một image nền đã có sẵn Tomcat 9 và Java 11
FROM tomcat:9.0-jdk11-temurin

# Xóa các ứng dụng web mặc định của Tomcat để tiết kiệm dung lượng
RUN rm -rf /usr/local/tomcat/webapps/*

# Sao chép file .war đã được Maven build vào thư mục webapps của Tomcat
# Maven sẽ build ra file .war trong thư mục /target
# Đổi tên file .war thành ROOT.war để nó chạy ngay tại địa chỉ gốc (/)
COPY target/BTC12.war /usr/local/tomcat/webapps/ROOT.war