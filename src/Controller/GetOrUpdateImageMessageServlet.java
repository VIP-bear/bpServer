package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBUtil.DBOperation;

/**
 * Servlet implementation class GetOrUpdateImageMessageServlet
 */
@WebServlet(name = "GetOrUpdateImageMessageServlet",value = "/AndroidTestDemo/GetOrUpdateImageMessageServlet")
public class GetOrUpdateImageMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOrUpdateImageMessageServlet() {
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
		String username = "";
		String imageName = null;
		int flag = 0;
		PrintWriter writer = response.getWriter();
		String msg = "";
		
		// 获取信息
		username = request.getParameter("username");
		imageName = request.getParameter("imageName");
		flag = Integer.valueOf(request.getParameter("flag"));
		
		try {
			if (flag == -1) {
				// 获取图片信息
				msg = DBOperation.getImageMessage(imageName);
				writer.print(msg);
			}else if (flag == 0){
					// 取消收藏
					DBOperation.DeleteCollectImage(username, imageName);
			}else {
				// 添加收藏
				DBOperation.InsertIntoCollectImage(username, imageName, 1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
