package cn.itcast.jdbc01.demo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc01_delete {

    public static void main(String[] args) throws SQLException {
        // 建立数据库连接 完成 添加一条记录
        // 1: 获取连接 DriverManager 加载驱动jar 获取Connection 对象
        Driver driver = new Driver();
        DriverManager.registerDriver(driver);
        // 2: 获取连接 Connection
        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/day08", "root", "root");
        // 3:编写sql Statement 对象 编译sql 发送给 mysql 数据库表
        String sql = "delete from user where id  = 4";
        Statement smt = con.createStatement();// executeUpdate insert delete update
        int n = smt.executeUpdate(sql);// n 表示更细一条记录... n>0 更新失败 n<0 发送给数据库 更新数据库...
        // 4: java 连接数据库 建立连接 消耗资源 关闭连接
        // 关闭资源顺序 遵循先开后关 ....
        if (smt != null) {
            smt.close();
        }
        if (con != null) {
            con.close();
        }

    }

}
