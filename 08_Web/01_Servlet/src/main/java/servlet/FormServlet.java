package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/form")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩 - 한글 처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String userId = request.getParameter("userId");
		System.out.println(userId);
		String userPwd = request.getParameter("userPwd");
		System.out.println(userPwd);
		String gender = request.getParameter("gender");
		System.out.println(gender);
		String[] menu = request.getParameterValues("menu");
		System.out.println(Arrays.toString(menu));
		
		// 삼항연산자를 사용해서 M/F 대신 남자/여자로 보이도록
		gender = gender.equals("M") ? "남자" : "여자";
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<p>" + "아이디 : " + userId + "</p>");
		out.println("<p>" + "비밀번호 : " + userPwd + "</p>");
		out.println("<p>" + "성별 : " + gender + "</p>");
		//out.println("<p>" + "좋아하는 메뉴 : " + String.join(", ", menu) + "</p>");
		// menu가 null이면 에러! null 아닐 때만 출력하도록 조건 걸기
		if (menu != null) {
			out.println("<p>좋아하는 메뉴</p>");
			out.println("<ul>");
			for (String m : menu) {
				out.println("<li>" + m + "</li>");
			}
			out.println("</ul>");
		}

		out.println("</body></html>");

		out.close();
	}
}
