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
 * �ϴ�ͷ��
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
		
		// �ļ��ϴ�����
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// ��ͬʱ�ϴ�����ļ�
			List<FileItem> list = upload.parseRequest(new ServletRequestContext(request));
			for (FileItem item : list) {
				fileName = item.getName();
				// ��ȡ������
				InputStream inputStream = item.getInputStream();
				File file = new File(pathname, fileName);
				OutputStream outputStream = new FileOutputStream(file);
				
				byte[] buf = new byte[1024];
				int length = 0;
				// д��
				while((length = inputStream.read(buf)) != -1) {
					outputStream.write(buf, 0, length);
				}
				inputStream.close();
				outputStream.close();
				msg += "�ϴ��ɹ�";
			}
		}catch (Exception e) {
			// TODO: handle exception\
			msg = "�ϴ�ʧ��";
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
