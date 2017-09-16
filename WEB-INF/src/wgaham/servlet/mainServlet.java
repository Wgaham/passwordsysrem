package wgaham.servlet;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import wgaham.javabean.*;

public class mainServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		dataBean dataBean = null;
		try {
			// dataBean=(Example7_2_Bean)request.getAttribute("resultBean")
			if (dataBean == null) {
				dataBean = new dataBean(); // 创建JavaBean对象
				request.setAttribute("dataBean", dataBean);
			}
		} catch (Exception exp) {
			dataBean = new dataBean(); // 创建JavaBean对象
			request.setAttribute("dataBean", dataBean);
		}
		HttpSession session = request.getSession(true);
		String userName = (String) session.getAttribute("userName");
		Connection con = null;
		Statement sql = null;
		ResultSet rs = null;
		String sqlGetData = "SELECT dataName,dataAccount,DECODE(dataPassword,'"
				+ userName + "') FROM " + userName + ";";
		try {
			String uri = "jdbc:mysql://127.0.0.1:3306/passwordsystem?user=root&password=&characterEncoding=UTF-8&useSSL=false";
			con = DriverManager.getConnection(uri);
			sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = sql.executeQuery(sqlGetData);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount(); // 得到结果集的列数
			rs.last();
			int rowNumber = rs.getRow(); // 得到记录数
			String[][] tableRecord = dataBean.getTableRecord();
			tableRecord = new String[rowNumber][columnCount];
			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {
				for (int k = 0; k < columnCount; k++)
					tableRecord[i][k] = rs.getString(k + 1);
				i++;
			}
			dataBean.setTableRecord(tableRecord);// 更新JavaBean数据模型
			con.close();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("showData.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
			response.setContentType("text/html;charset = UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");// 输出的内容要放在body中
			out.println("<body>");
			out.println("<center>");
			out.println("登录失效！3秒后返回登录界面");
			out.println("<br><image src = 'image/error.jpg'></image>");
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
			response.setHeader("refresh", "3;url=login.jsp");
			out.close();
			return;
		}
		// System.out.println(sqlGetData);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Connection con = null;
		Statement sql = null;
		ResultSet rs = null;
		boolean flag1 = false;
		try {
			String uri = "jdbc:mysql://127.0.0.1:3306/passwordsystem?user=root&password=&characterEncoding=UTF-8&useSSL=false";
			con = DriverManager.getConnection(uri);
			sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			System.out.println(e);
		}
		String flag = request.getParameter("flag");
		HttpSession session = request.getSession(true);
		String userName = (String) session.getAttribute("userName");
		if (flag.equals("insert")) {
			String dataName = request.getParameter("dataName");
			String dataAccount = request.getParameter("dataAccount");
			String dataPassword = request.getParameter("dataPassword");
			String sqlInsertCheck = "SELECT dataName FROM " + userName
					+ "WHERE dataName='" + dataName + "';";
			String sqlInsertData = "INSERT INTO " + userName + " VALUES('"
					+ dataName + "','" + dataAccount + "',ENCODE('"
					+ dataPassword + "','" + userName + "'));";
			// System.out.println(sqlInsertCheck);
			try {
				rs = sql.executeQuery(sqlInsertCheck);
				if (rs.next()) {
					flag1 = true;
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			if (flag1 == true) {
				response.setContentType("text/html;charset = UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<html>");// 输出的内容要放在body中
				out.println("<body>");
				out.println("<center>");
				out.println("已存在该钥匙，添加失败！3秒后返回");
				out.println("<br><image src = 'image/error.jpg'></image>");
				out.println("</center>");
				out.println("</body>");
				out.println("</html>");
				try {
					con.close();
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				response.setHeader("refresh", "3;url=insertData.jsp");
				out.close();
				return;
			}
			try {
				sql.executeUpdate(sqlInsertData);
				con.close();

			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			response.setContentType("text/html;charset = UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");// 输出的内容要放在body中
			out.println("<body>");
			out.println("<center>");
			out.println("钥匙添加成功！3秒后返回");
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
			response.setHeader("refresh", "3;url=mainServlet");
			out.close();
			return;
		} else if (flag.equals("delete")) {
			String dataName = request.getParameter("dataName");
			String sqlDeleteCheck = "SELECT dataName FROM " + userName
					+ " WHERE dataName='" + dataName + "';";
			String sqlDeleteData = "DELETE FROM " + userName
					+ " WHERE dataName='" + dataName + "';";
			// System.out.println(sqlDeleteCheck);
			try {
				rs = sql.executeQuery(sqlDeleteCheck);
				if (rs.next()) {
					flag1 = true;
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			if (flag1 == false) {
				response.setContentType("text/html;charset = UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<html>");// 输出的内容要放在body中
				out.println("<body>");
				out.println("<center>");
				out.println("不存在该钥匙，删除失败！3秒后返回");
				out.println("<br><image src = 'image/error.jpg'></image>");
				out.println("</center>");
				out.println("</body>");
				out.println("</html>");
				try {
					con.close();
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				response.setHeader("refresh", "3;url=deleteData.jsp");
				out.close();
				return;
			}
			try {
				sql.executeUpdate(sqlDeleteData);
				con.close();

			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			response.setContentType("text/html;charset = UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");// 输出的内容要放在body中
			out.println("<body>");
			out.println("<center>");
			out.println("钥匙删除成功！3秒后返回");
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
			response.setHeader("refresh", "3;url=mainServlet");
			out.close();
			return;
		} else if (flag.equals("update")) {
			String dataName = request.getParameter("dataName");
			String dataAccount = request.getParameter("dataAccount");
			String dataPassword = request.getParameter("dataPassword");
			String sqlUpdateCheck = "SELECT dataName FROM " + userName
					+ " WHERE dataName='" + dataName + "';";
			String sqlUpdateAccount = "UPDATE " + userName
					+ " SET dataAccount='" + dataAccount + "'WHERE dataName='"
					+ dataName + "';";
			String sqlUpdatePassword = "UPDATE " + userName
					+ " SET dataPassword=ENCODE('" + dataPassword + "','"
					+ userName + "') WHERE dataName='" + dataName + "';";
			String sqlUpdateAll = "UPDATE " + userName + " SET dataAccount='"
					+ dataAccount + "', dataPassword=ENCODE('" + dataPassword
					+ "','" + userName + "') WHERE dataName='" + dataName
					+ "';";
			try {
				rs = sql.executeQuery(sqlUpdateCheck);
				if (rs.next()) {
					flag1 = true;
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			if (flag1 == false) {
				response.setContentType("text/html;charset = UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<html>");// 输出的内容要放在body中
				out.println("<body>");
				out.println("<center>");
				out.println("不存在该钥匙，修改失败！3秒后返回");
				out.println("<br><image src = 'image/error.jpg'></image>");
				out.println("</center>");
				out.println("</body>");
				out.println("</html>");
				try {
					con.close();
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				response.setHeader("refresh", "3;url=updateData.jsp");
				out.close();
				return;
			}
			if (dataAccount == null || dataAccount.isEmpty()) {
				try {
					sql.executeUpdate(sqlUpdatePassword);
					con.close();

				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			} else if (dataPassword == null || dataPassword.isEmpty()) {
				try {
					sql.executeUpdate(sqlUpdateAccount);
					con.close();

				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			try {
				sql.executeUpdate(sqlUpdateAll);
				con.close();

			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			response.setContentType("text/html;charset = UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");// 输出的内容要放在body中
			out.println("<body>");
			out.println("<center>");
			out.println("修改成功！3秒后返回");
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
			response.setHeader("refresh", "3;url=mainServlet");
			out.close();
			return;
		} else if (flag.equals("user")) {
			String userPassword = request.getParameter("newPassword");
			String sqlUpdateUserPassword = "UPDATE user SET userPassword=ENCODE('"
					+ userPassword
					+ "','wgaham') WHERE userName='"
					+ userName
					+ "';";
			try {
				sql.executeUpdate(sqlUpdateUserPassword);
				con.close();

			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			session.invalidate();
			response.setContentType("text/html;charset = UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");// 输出的内容要放在body中
			out.println("<body>");
			out.println("<center>");
			out.println("修改成功！3秒后返回登录页面");
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
			response.setHeader("refresh", "3;url=login.jsp");
			out.close();
			return;
		}
	}
}