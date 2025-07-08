package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.Member;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.MemberDAO;

@WebServlet("/view")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// DB 정보 필요 -> DAO
			MemberDAO dao = new MemberDAO();
			
			List<Member> list = dao.viewAll();
			
			// 바인딩 -> 이 정보가 필요한지
			request.setAttribute("list", list);
			
			// 네비게이션 -> view.jsp
			request.getRequestDispatcher("view.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
