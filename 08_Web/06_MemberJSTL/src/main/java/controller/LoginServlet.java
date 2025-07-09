package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vo.Member;

import java.io.IOException;
import java.sql.SQLException;

import dao.MemberDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		System.out.println(id);
		System.out.println(pwd);

		MemberDAO dao = new MemberDAO();
		try {		
			Member member = dao.login(id, pwd);
			
			// Session - 바인딩
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			
			response.sendRedirect("index.jsp");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
