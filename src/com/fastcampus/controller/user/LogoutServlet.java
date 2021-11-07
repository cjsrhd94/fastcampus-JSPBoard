package com.fastcampus.controller.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout.do")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 로그아웃을 요청한 브라우저와 매핑된 세션을 강제로 종료한다.
        HttpSession session = request.getSession();
        session.invalidate();

        response.sendRedirect("login.html");
    }
}
