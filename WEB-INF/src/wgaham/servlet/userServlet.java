package wgaham.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import wgaham.javabean.userBean;

public class userServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			response.setContentType("text/html;charset = UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");// 输出的内容要放在body中
			out.println("<body>");
			out.println("<center>");
			out.println("后台错误！3秒后返回");
			out.println("<br><image src = 'image/error.jpg'></image>");
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
			response.setHeader("refresh", "3;url=login.jsp");
			out.close();
			return;
		}

	}
}
