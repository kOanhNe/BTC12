package DAO;

import java.sql.*;

public class GatewayDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/my_database?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "kieuoanh"; // Thay mật khẩu của bạn

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public String executeStatement(String sql) {
        if (sql == null || sql.trim().isEmpty()) {
            return "<p>Vui lòng nhập câu lệnh SQL.</p>";
        }

        // Chỉ cho phép câu lệnh SELECT để bảo mật
        if (!sql.trim().toLowerCase().startsWith("select")) {
            return "<p style='color: red;'>Lỗi: Chỉ cho phép thực thi câu lệnh SELECT.</p>";
        }

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            StringBuilder tableHtml = new StringBuilder();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            tableHtml.append("<table class='result-table'>");
            
            tableHtml.append("<thead><tr>");
            for (int i = 1; i <= columnCount; i++) {
                tableHtml.append("<th>").append(metaData.getColumnName(i)).append("</th>");
            }
            tableHtml.append("</tr></thead>");

            tableHtml.append("<tbody>");
            while (rs.next()) {
                tableHtml.append("<tr>");
                for (int i = 1; i <= columnCount; i++) {
                    tableHtml.append("<td>").append(rs.getString(i) == null ? "" : rs.getString(i)).append("</td>");
                }
                tableHtml.append("</tr>");
            }
            tableHtml.append("</tbody></table>");

            return tableHtml.toString();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "<p style='color: red;'>Lỗi khi thực thi câu lệnh: " + e.getMessage() + "</p>";
        }
    }
}