package cn.itcast.jdbc01.demo;

import cn.itcast.jdbc01.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc01_queryall {

    public static void main(String[] args) throws SQLException {
        // 建立数据库连接 完成 添加一条记录
        // 1: 获取连接 DriverManager 加载驱动jar 获取Connection 对象
        // Driver driver = new Driver();
        // DriverManager.registerDriver(driver);
        // // 2: 获取连接 Connection
        // Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/day08", "root", "root");
        Connection con = JdbcUtils.getConnection();
        // 3:编写sql Statement 对象 编译sql 发送给 mysql 数据库表
        String sql = "select * from user";// 查询单条记录
        // String sql = "select * from user";// 查询多条记录
        Statement smt = con.createStatement();// executeUpdate insert delete update
        ResultSet rs = smt.executeQuery(sql);
        // 4: 数据库结果 全部封装 ResultSet 对象 ---> 程序员 对象数据 遍历出来 --->封装 实体类中!!
        while (rs.next()) {
            System.out.println("yonghu  id  = " + rs.getInt("id") + "----user name  = " + rs.getString("name") + "----user password = " + rs.getString("password"));
            System.out.println("---------------------------------");
        }
        // 存在该记录 +
        // 5: java 连接数据库 建立连接 消耗资源 关闭连接
        // 关闭资源顺序 遵循先开后关 ....
        if (rs != null) {
            rs.close();
        }
        if (smt != null) {
            smt.close();
        }
        if (con != null) {
            con.close();
        }
    }
}
