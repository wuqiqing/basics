package cn.itcast.jdbc01.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

//  工具类 只能在mysql 使用 要求用户必须 root  密码 必须是root   必须数据库名称day08
public class JdbcUtils {
    static String url;
    static String username;
    static String password;

    static {
        // 类加载 代码只执行 一次
        try {
            Class.forName(ResourceBundle.getBundle("db").getString("driverClass"));// 加载字节码 ---Class
            url = ResourceBundle.getBundle("db").getString("url");
            username = ResourceBundle.getBundle("db").getString("username");
            password = ResourceBundle.getBundle("db").getString("password");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e + "加载驱动失败");
        }

    }

    // 分析 crud 操作 核心对象....是哪一个>???? Connection 重点抽取的对象
    // 获取connection 对象

    /**
     * 抽取工具类 代码需要数据库连接数据 不能写死 代码不需要改动 就可以 通过工具类 连接不同的数据库 不同 用户 不同密码 不同数据库名称 引入配置文件
     *
     * @return
     */
    // 引入配置文件... 修改配置文件 不需要修改源代码 就可以实现不同数据库连接 操作不同数据库
    // 配置文件 .txt .properties Properties, xml(可以 但是dom4j+xpath 掌握知识...)
    // properties key = value 解析不需要导外置包 不需要新的技术 jdk 搞定...
    public static Connection getConnection() {
        try {
            // Driver driver = new Driver(); // 代码不要写 ..依赖Mysql Drvier 仍然执行static 代码块执行...
            // 反射!! 加载字节码文件 Class ---> static
            // Class.forName(ResourceBundle.getBundle("db").getString("driverClass"));// 加载字节码 ---Class
            // DriverManager.registerDriver(driver);
            // 2: 获取连接 Connection 三个信息 想办法 从配置文件中读取出来 放在指定的位置
            // 3: Properties
            // InputStream in = new FileInputStream(new File("src/db.properties"));
            // Properties prop = new Properties();
            // prop.load(in);
            // String value = prop.getProperty("username");
            // System.out.println(value);
            // 4: 简化开发 jdk ResouceBundle 类专门加载properties 自动加载 src 下文件 src 专属存放用户配置文件的 扩展名省略
            // String value = ResourceBundle.getBundle("db").getString("username");
            Connection con = (Connection) DriverManager.getConnection(url, username, password);
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取连接失败");
        }
    }

    // 关闭资源方法 ...
    public static void closeConnection(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }

    public static void main(String[] args) {
        Connection con = getConnection();
        System.out.println(con);
    }
}
