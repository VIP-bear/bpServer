package DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMysql {
	// 声明Connection对象
	Connection connection;
			
	// 驱动程序名
	String driver = "com.mysql.cj.jdbc.Driver";
	
	// URL指向要访问的数据库名
	String url = "jdbc:mysql://182.92.159.2:3306/bp?useSSL=false&serverTimezone=UTC";
	
	// Mysql配置时的用户名
	String user = "root";
	
	// Mysql配置时的密码
	String password = "123456";
	
	public ConnectMysql() {
		createConnect();
	}
	
	// 连接数据库
	private void createConnect() {
		try {
			// 加载驱动程序
			Class.forName(driver);
			
			// 连接数据库
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("数据库连接成功！");
			
		}catch(ClassNotFoundException e){
			// 数据库驱动异常
			e.printStackTrace();
		}catch(SQLException e) {
			// 数据库连接失败
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection() {
		return connection;
	}
	public void close() throws SQLException {
		connection.close();
	}
}
