package DBUtil;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBOperation {
	
	// ע��
	public static String insertIntoUser(String username,String password) throws SQLException {
		String result = "ע��ʧ��";
		int row = 0;
		ConnectMysql connectMysql = new ConnectMysql();
		
		String sql="insert ignore into user values (?, ?)";
		PreparedStatement pStatement = connectMysql.getConnection().prepareStatement(sql);
		
		pStatement.setString(1, username);
		pStatement.setString(2, password);
		row = pStatement.executeUpdate();	// �ɹ�����һ������ʱ����1��ʧ�ܷ���0
		
		if (row == 1) {
			result = "ע��ɹ�";
		}
		return result;
	}
	
	// ��¼
	public static String login(String username,String password) throws SQLException {
		String result = "��¼ʧ��";
		ConnectMysql connectMysql = new ConnectMysql();
		String sql = "select * from user where username=? and password=?";
		PreparedStatement pStatement = connectMysql.getConnection().prepareStatement(sql);
		
		pStatement.setString(1, username);
		pStatement.setString(2, password);
		
		ResultSet rt = pStatement.executeQuery();
		// ��½�ɹ�
		if (rt.next()) {
			result = "��¼�ɹ�";
		}else {
			result = "��¼ʧ��";
		}
		rt.close();
		pStatement.close();
		connectMysql.close();
		return result;
	}
	
	// ��ȡָ��Ŀ¼��ȫ���ļ�������
	public static String getAllImageName() {
		StringBuilder stringBuilder = new StringBuilder();
		String path = "/home/image/uploadImage";
		File file = new File(path);
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			stringBuilder.append(files[i].getName()+" ");
		}
		return stringBuilder.toString().trim();
	}
	
	// ��ͼƬ��Ϣ���в�������
	public static void InsertIntoImageMessage(String imagename, String label,
			String title, String description, String username) throws SQLException {
		ConnectMysql connectMysql = new ConnectMysql();
		
		String sql="insert ignore into image_message values (?, ?, ?, ?, ?)";
		PreparedStatement pStatement = connectMysql.getConnection().prepareStatement(sql);
		
		pStatement.setString(1, imagename);
		pStatement.setString(2, label);
		pStatement.setString(3, title);
		pStatement.setString(4, description);
		pStatement.setString(5, username);
		pStatement.executeUpdate();
	}
	
	// ��collect_image_message���в�������(�ղ�ͼƬ)
	public static void InsertIntoCollectImage(String username, 
			String imagename, int collect) throws SQLException {
		ConnectMysql connectMysql = new ConnectMysql();
		
		String sql="insert ignore into collect_image_message values (?, ?, ?)";
		PreparedStatement pStatement = connectMysql.getConnection().prepareStatement(sql);
		
		pStatement.setString(1, username);
		pStatement.setString(2, imagename);
		pStatement.setInt(3, collect);
		pStatement.executeUpdate();
	}
	
	// ��collect_image_message����ɾ������(ȡ���ղ�)
		public static void DeleteCollectImage(String username, 
				String imagename) throws SQLException {
			ConnectMysql connectMysql = new ConnectMysql();
			
			String sql="delete from collect_image_message where username=? and imagename=?";
			PreparedStatement pStatement = connectMysql.getConnection().prepareStatement(sql);
			
			pStatement.setString(1, username);
			pStatement.setString(2, imagename);
			pStatement.executeUpdate();
		}
	
	// ��ȡ����ͼƬ����Ϣ
	public static String getImageMessage(String imageName) throws SQLException {
		ConnectMysql connectMysql = new ConnectMysql();
		
		String sql="select * from image_message where imagename=?";
		PreparedStatement pStatement = connectMysql.getConnection().prepareStatement(sql);
		
		pStatement.setString(1, imageName);
		ResultSet rSet = pStatement.executeQuery();
		if (rSet.next()) {
			return rSet.getString("label") + "#@" + rSet.getString("title")
					+ "#@" + rSet.getString("description") + "#@";
		}else {
			return null;
		}
	}
	
	// ��ȡ�ղ�ͼƬ������
	public static String getCollectImageName(String username) throws SQLException {
		StringBuilder stringBuilder = new StringBuilder();
		ConnectMysql connectMysql = new ConnectMysql();
		
		String sql="select imagename from collect_image_message where username=?";
		PreparedStatement pStatement = connectMysql.getConnection().prepareStatement(sql);
		
		pStatement.setString(1, username);
		ResultSet rSet = pStatement.executeQuery();
		while(rSet.next()) {
			stringBuilder.append(rSet.getString("imagename")+" ");
		}
		return stringBuilder.toString().trim();
	}

}
