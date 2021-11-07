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
    } else {
        response.sendRedirect("login.html");
    }
%>

