package wgaham.servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

//import wgaham.javabean.*;
public class registerServlet extends HttpServlet {
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
		}
		String userName = request.getParameter("newUsername");
		String userPassword = request.getParameter("newPassword");
		// String userQuestion = request.getParameter("pwQuestion");
		// String userAnswer = request.getParameter("newAnswer");
		Connection con = null;
		Statement sql = null;
		ResultSet rs = null;
		boolean flag = false;
		String sqlusercheck = "SELECT userName From user WHERE userName='"
				+ userName + "';";
		String sqluserinsert = "INSERT INTO user VALUES('" + userName
				+ "',ENCODE('" + userPassword + "','wgaham'));";
		String sqldata = "CREATE TABLE "
				+ userName
				+ " (dataname varchar(20)CHARACTER SET utf8 COLLATE utf8_bin NOT NULL PRIMARY KEY,dataAccount varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,dataPassword blob NOT NULL);";

		try {
			String uri = "jdbc:mysql://127.0.0.1:3306/passwordsystem?user=root&password=&characterEncoding=UTF-8&useSSL=false";
			con = DriverManager.getConnection(uri);
			sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = sql.executeQuery(sqlusercheck);
			if (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		if (flag == true) {
			response.setContentType("text/html;charset = UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");// 输出的内容要放在body中
			out.println("<body>");
			out.println("<center>");
			out.println("已存在该用户名，注册失败！3秒后返回");
			out.println("<br><image src = 'image/error.jpg'></image>");
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
			response.setHeader("refresh", "3;url=register.jsp");
			out.close();
			return;
		}
		// sql=null;
		// rs=null;
		try {
			sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sql.executeUpdate(sqluserinsert);
			sql = null;
			rs = null;
			sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			// System.out.println(sqldata);
			sql.executeUpdate(sqldata);
			con.close();
			response.setContentType("text/html;charset = UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");// 输出的内容要放在body中
			out.println("<body>");
			out.println("<center>");
			out.println("注册成功！3秒后进入登录页面");
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
			response.setHeader("refresh", "3;url=login.jsp");
			out.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
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
