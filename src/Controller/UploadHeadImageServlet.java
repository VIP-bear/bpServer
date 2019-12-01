package Controller;

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

import java.io.*;

/**
 * Servlet implementation class UploadHeadImageServlet
 * 上传头像
 */
@WebServlet(name = "UploadHeadImageServlet",value = "/AndroidTestDemo/UploadHeadImageServlet")
public class UploadHeadImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadHeadImageServlet() {
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
		String fileName = "";
		String pathname = "/home/image/headImage/";
		PrintWriter writer = response.getWriter();
		String msg = "";
	
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// 文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// 可同时上传多个文件
			List<FileItem> list = upload.parseRequest(new ServletRequestContext(request));
			for (FileItem item : list) {
				fileName = item.getName();
				// 获取输入流
				InputStream inputStream = item.getInputStream();
				File file = new File(pathname, fileName);
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
			}
		}catch (Exception e) {
			// TODO: handle exception\
			msg = "上传失败";
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
