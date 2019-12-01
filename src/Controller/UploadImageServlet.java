package Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import DBUtil.DBOperation;

/**
 * Servlet implementation class UploadImageServlet
 * 上传图片
 */
@WebServlet(name = "UploadImageServlet",value = "/AndroidTestDemo/UploadImageServlet")
public class UploadImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username = null;
		String imageName = null;
		String label = null;
		String title = null;
		String description = null;
		String pathname = "/home/image/uploadImage/";
		PrintWriter writer = response.getWriter();
		String msg = "";
	
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// 可同时上传多个文件
			List<FileItem> list = upload.parseRequest(new ServletRequestContext(request));
			// 普通文本字段
			username = list.get(0).getString();
			label = list.get(1).getString();
			title = list.get(2).getString();
			description = list.get(3).getString();
		
			// 图片
			FileItem item = list.get(4);
			imageName = item.getName();
			// 获取输入流
			InputStream inputStream = item.getInputStream();
			File file = new File(pathname, imageName);
			OutputStream outputStream = new FileOutputStream(file);
			
			byte[] buf = new byte[1024];
			int length = 0;
			// 写入
			while((length = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, length);
			}
			inputStream.close();
			outputStream.close();
			msg += "上传成功";
		}catch (Exception e) {
			// TODO: handle exception\
			msg += "上传失败";
		}
		
		// 将图片信息保存到数据库的image_message和collect_upload_image表中
		try {
			DBOperation.InsertIntoImageMessage(imageName, label, title, description, username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writer.print(msg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
