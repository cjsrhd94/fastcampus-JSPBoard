<%@ page import="com.fastcampus.biz.board.BoardVO" %>
<%@ page import="com.fastcampus.biz.board.BoardDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 1. 사용자 입력정보 추출
    String title = request.getParameter("title");
    String seq = request.getParameter("seq");
    String content = request.getParameter("content");

    // 2. DB 연동 처리
    BoardVO vo = new BoardVO();
    vo.setTitle(title);
    vo.setSeq(Integer.parseInt(seq));
    vo.setContent(content);

    BoardDAO boardDAO = new BoardDAO();
    boardDAO.updateBoard(vo);

    // 3. 화면 네비게이션
    response.sendRedirect("getBoardList.jsp");
%>

