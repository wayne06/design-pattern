package d.designpattern.structural.bridge.v1;

import java.sql.*;

public class JdbcDemo {

    public static void main(String[] args) {
        try {
            // 更改一行代码就可换成别的数据库，就是依靠桥接模式实现
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test?user=root&password=root";
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            String query = "select * from test";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                resultSet.getString(1);
                resultSet.getInt(2);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }


}
