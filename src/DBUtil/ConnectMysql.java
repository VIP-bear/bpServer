package DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMysql {
	// ����Connection����
	Connection connection;
			
	// ����������
	String driver = "com.mysql.cj.jdbc.Driver";
	
	// URLָ��Ҫ���ʵ����ݿ���
	String url = "jdbc:mysql://182.92.159.2:3306/bp?useSSL=false&serverTimezone=UTC";
	
	// Mysql����ʱ���û���
	String user = "root";
	
	// Mysql����ʱ������
	String password = "123456";
	
	public ConnectMysql() {
		createConnect();
	}
	
	// �������ݿ�
	private void createConnect() {
		try {
			// ������������
			Class.forName(driver);
			
			// �������ݿ�
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("���ݿ����ӳɹ���");
			
		}catch(ClassNotFoundException e){
			// ���ݿ������쳣
			e.printStackTrace();
		}catch(SQLException e) {
			// ���ݿ�����ʧ��
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
