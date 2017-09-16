package wgaham.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import wgaham.javabean.userBean;

public class loginServlet extends HttpServlet {
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
			// out.println(sqlUserExist);
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
			response.setHeader("refresh", "3;url=login.jsp");
			out.close();
			return;
		}
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		Connection con = null;
		Statement sql = null;
		ResultSet rs = null;
		boolean flag = false;
		String sqlUserExist = "SELECT userName,DECODE(userPassword,'wgaham') FROM user WHERE userName='"
				+ userName
				+ "' AND DECODE(userPassword,'wgaham')='"
				+ userPassword + "';";
		try {
			String uri = "jdbc:mysql://127.0.0.1:3306/passwordsystem?user=root&password=&characterEncoding=UTF-8&useSSL=false";
			con = DriverManager.getConnection(uri);
			sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = sql.executeQuery(sqlUserExist);

			if (rs.next()) {
				flag = true;
			}
			if (flag == false) {
				response.setContentType("text/html;charset = UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<html>");// 输出的内容要放在body中
				out.println("<body>");
				out.println("<center>");
				out.println("用户名或密码不匹配，登陆失败！3秒后返回");
				out.println("<br><image src = 'image/error.jpg'></image>");
				out.println("</center>");
				out.println("</body>");
				out.println("</html>");
				response.setHeader("refresh", "3;url=login.jsp");
				out.close();
				return;
			}

		} catch (SQLException e) {
			System.out.println(e);
			response.setContentType("text/html;charset = UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");// 输出的内容要放在body中
			out.println("<body>");
			out.println("<center>");
			out.println("系统错误！3秒后返回");
			out.println("<br><image src = 'image/error.jpg'></image>");
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
			response.setHeader("refresh", "3;url=login.jsp");
			out.close();
			return;
		}
		userBean userData = null;
		userData = new userBean();
		HttpSession session = request.getSession(true);
		session.setAttribute("userName", userName);
		// session.setAttribute("userPassword", userPassword);
		userData.setUserName(userName);
		userData.setUserPassword(userPassword);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");// 输出的内容要放在body中
		out.println("<body>");
		out.println("<center>");
		out.println("登录成功！ 3秒后跳转");
		// out.println(sqlUserExist);
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		response.setHeader("refresh", "3;url=mainServlet");
		out.close();
		// System.out.println("登录成功！ 3秒后跳转");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset = UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");// 输出的内容要放在body中
		out.println("<body>");
		out.println("<center>");
		out.println("非法访问！！");
		out.println("<br><image src = 'image/error.jpg'></image>");
		// out.println(sqlUserExist);
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		response.setHeader("refresh", "3;url=login.jsp");
		out.close();
	}
}