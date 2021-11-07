<%@ page import="com.fastcampus.biz.user.UserVO" %>
<%@ page import="com.fastcampus.biz.user.UserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
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
%>

