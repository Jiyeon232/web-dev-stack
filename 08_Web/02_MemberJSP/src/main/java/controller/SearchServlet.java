package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.Member;

import java.io.IOException;
import java.sql.SQLException;

import dao.MemberDAO;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// 1. 폼 값 받아와야 함
			String id = request.getParameter("id");
			
			// 2. 디비에서 정보 조회 -> DAO 필요
			MemberDAO dao = new MemberDAO();
			Member member = dao.search(id);
			
			// 3. 바인딩
			request.setAttribute("member", member);
			
			// 4. search.jsp
			request.getRequestDispatcher("search.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
