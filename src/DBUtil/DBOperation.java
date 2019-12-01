package DBUtil;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBOperation {
	
	// 注册
	public static String insertIntoUser(String username,String password) throws SQLException {
		String result = "注册失败";
		int row = 0;
		ConnectMysql connectMysql = new ConnectMysql();
		
		String sql="insert ignore into user values (?, ?)";
		PreparedStatement pStatement = connectMysql.getConnection().prepareStatement(sql);
		
		pStatement.setString(1, username);
		pStatement.setString(2, password);
		row = pStatement.executeUpdate();	// 成功插入一条数据时返回1，失败返回0
		
		if (row == 1) {
			result = "注册成功";
		}
		return result;
	}
	
	// 登录
	public static String login(String username,String password) throws SQLException {
		String result = "登录失败";
		ConnectMysql connectMysql = new ConnectMysql();
		String sql = "select * from user where username=? and password=?";
		PreparedStatement pStatement = connectMysql.getConnection().prepareStatement(sql);
		
		pStatement.setString(1, username);
		pStatement.setString(2, password);
		
		ResultSet rt = pStatement.executeQuery();
		// 登陆成功
		if (rt.next()) {
			result = "登录成功";
		}else {
			result = "登录失败";
		}
		rt.close();
		pStatement.close();
		connectMysql.close();
		return result;
	}
	
	// 获取指定目录下全部文件的名字
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
	
	// 在图片信息表中插入数据
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
	
	// 在collect_image_message表中插入数据(收藏图片)
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
	
	// 在collect_image_message表中删除数据(取消收藏)
		public static void DeleteCollectImage(String username, 
				String imagename) throws SQLException {
			ConnectMysql connectMysql = new ConnectMysql();
			
			String sql="delete from collect_image_message where username=? and imagename=?";
			PreparedStatement pStatement = connectMysql.getConnection().prepareStatement(sql);
			
			pStatement.setString(1, username);
			pStatement.setString(2, imagename);
			pStatement.executeUpdate();
		}
	
	// 获取单张图片的信息
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
	
	// 获取收藏图片的名字
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
