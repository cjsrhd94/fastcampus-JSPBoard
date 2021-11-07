package com.fastcampus.controller;

import com.fastcampus.biz.user.UserDAO;
import com.fastcampus.biz.user.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "*.do")
public class DispatcherServlet extends HttpServlet {
    public DispatcherServlet() {
        System.out.println("===> DispatcherServlet 생성");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 사용자가 요청한 path 정보를 추출한다.
        String uri = request.getRequestURI();
        String path = uri.substring(uri.lastIndexOf("/"));
        System.out.println("요청 PATH : " + path);

        // 2. 요청 path에 해당하는 로직으로 분기처리한다.
        HttpSession session = request.getSession();

        if (path.equals("/login.do")) {
            System.out.println("로그인 기능 처리");

            // 1. 사용자 입력정보 추출
            String id = request.getParameter("id");
            String password = request.getParameter("password");

            // 2. DB 연동 처리
            UserVO vo = new UserVO();
            vo.setId(id);
            vo.setPassword(password);

            UserDAO userDAO = new UserDAO();
            UserVO user = userDAO.getUser(vo);

            // 3. 응답 화면 구성
            if (user != null) {
                response.sendRedirect("getBoardList.jsp");

                // 로그인 성공시 세션(내장 객체)에 사용자 정보를 저장한다.
                session.setAttribute("user", user);
            } else {
                response.sendRedirect("login.html");
            }

        } else if (path.equals("/logout.do")) {
            System.out.println("로그아웃 기능 처리");
        } else if (path.equals("/insertBoard.do")) {
            System.out.println("글 등록 기능 처리");
        } else if (path.equals("/updateBoard.do")) {
            System.out.println("글 수정 기능 처리");
        } else if (path.equals("/deleteBoard.do")) {
            System.out.println("글 삭제 기능 처리");
        } else if (path.equals("/getBoard.do")) {
            System.out.println("글 상세 조회 기능 처리");
        } else if (path.equals("/getBoardList.do")) {
            System.out.println("글 목록 검색 기능 처리");
        } else {
            System.out.println("path에 해당하는 로직이 없습니다.");
        }

    }
}

