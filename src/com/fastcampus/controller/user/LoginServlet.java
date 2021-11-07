package com.fastcampus.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fastcampus.biz.user.UserDAO;
import com.fastcampus.biz.user.UserVO;

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 사용자 입력정보(id, password) 추출
		// 사용자가 입력한 파라미터 정보를 추출한다.
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		// 2. DB 연동 처리
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		
		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.getUser(vo);
		
//		// 3. 응답 화면 구성
//		// 출력 스트림을 얻기 전에 응답 메시지에 대한 인코딩을 설정한다.
//		response.setContentType("text/html;charset=EUC-KR");
//
//		// Http 응답 프로토콜 Body와 연결된 출력 스트림을 얻는다.
//		PrintWriter out = response.getWriter();

		RequestDispatcher dispatcher = null;
		if(user != null) {
			// 로그인 성공한 경우, 세션에 userID 정보를 등록한다.
			HttpSession session = request.getSession();
//			session.setMaxInactiveInterval(10);	// 언제까지 세션을 유지할 것인가 (단위 : 초)
			session.setAttribute("userID", user.getId());
			session.setAttribute("userName", user.getName());
			session.setAttribute("userRole", user.getRole());
			dispatcher = request.getRequestDispatcher("/getBoardList.do");
//			response.sendRedirect("getBoardList.do");
		} else {
			dispatcher = request.getRequestDispatcher("/login.html");
//			response.sendRedirect("login.html");
		}
		dispatcher.forward(request,response);

//		out.close();
	}

}






