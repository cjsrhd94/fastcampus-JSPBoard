<%@ page import="com.fastcampus.biz.board.BoardVO" %>
<%@ page import="com.fastcampus.biz.board.BoardDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 1. 사용자 입력정보 추출
    String seq = request.getParameter("seq");

    // 2. DB 연동 처리
    BoardVO vo = new BoardVO();
    vo.setSeq(Integer.parseInt(seq));

    BoardDAO boardDAO = new BoardDAO();
    boardDAO.deleteBoard(vo);

    // 3. 화면 네비게이션
    response.sendRedirect("getBoardList.jsp");
%>