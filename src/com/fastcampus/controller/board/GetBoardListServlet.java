package com.fastcampus.controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fastcampus.biz.board.BoardDAO;
import com.fastcampus.biz.board.BoardVO;

@WebServlet(urlPatterns = "/getBoardList.do")
public class GetBoardListServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 0. 세션 체크
        // 글목록을 요청한 브라우저와 매핑된 세션을 검색한다. (없으면 새로 생성한다.)
        HttpSession session = request.getSession();

        // 세션에 등록된 userID 정보를 통해 로그인 상태를 확인한다.
        if (session.getAttribute("userID") == null) {
            response.sendRedirect("login.html");
        } else {

            // 1. 사용자 입력정보 추출
            String searchCondition = request.getParameter("searchCondition");
            String searchKeyword = request.getParameter("searchKeyword");

            // Null check
            if (searchCondition == null) searchCondition = "TITLE";
            if (searchKeyword == null) searchKeyword = "";

            // 2. DB 연동 처리
            BoardVO vo = new BoardVO();
            vo.setSearchCondition(searchCondition);
            vo.setSearchKeyword(searchKeyword);

            BoardDAO boardDAO = new BoardDAO();
            List<BoardVO> boardList = boardDAO.getBoardList(vo);

            // 3. 응답 화면 구성
            // 출력 스트림을 얻기 전에 응답 메시지에 대한 인코딩을 설정한다.
            response.setContentType("text/html;charset=EUC-KR");

            // Http 응답 프로토콜 Body와 연결된 출력 스트림을 얻는다.
            PrintWriter out = response.getWriter();

            out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv='Content-Type' content='text/html; charset=EUC-KR'>");
            out.println("<title>글 목록</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");
            out.println("<h1>게시 글 목록</h1>");
            out.println("<h3><font color='red'>" + session.getAttribute("userName") + " </font>님 로그인 환영합니다...<a href='logout.do'>LOG-OUT</a></h3>");
            out.println("<!-- 검색 시작 -->");
            out.println("<form action='getBoardList.do' method='post'>");
            out.println("<table border='1' cellpadding='0' cellspacing='0' width='700'>");
            out.println("<tr>");
            out.println("<td align='right'>");
            out.println("<select name='searchCondition'>");
            out.println("<option value='TITLE'>제목</option>");
            out.println("<option value='CONTENT'>내용</option>");
            out.println("</select>");
            out.println("<input name='searchKeyword' type='text'/>");
            out.println("<input type='submit' value='검색'/>");
            out.println("</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</form>");
            out.println("<!-- 검색 종료 -->");
            out.println("<table border='1' cellpadding='0' cellspacing='0' width='700'>");
            out.println("<tr>");
            out.println("<th bgcolor='orange' width='100'>번호</th>");
            out.println("<th bgcolor='orange' width='200'>제목</th>");
            out.println("<th bgcolor='orange' width='150'>작성자</th>");
            out.println("<th bgcolor='orange' width='150'>등록일</th>");
            out.println("<th bgcolor='orange' width='100'>조회수</th>");
            out.println("</tr>");

            for (BoardVO board : boardList) {
                out.println("<tr>");
                out.println("<td>" + board.getSeq() + "</td>");
                out.println("<td align='left'><a href='getBoard.do?seq=" + board.getSeq() + "'>" + board.getTitle() + "</a></td>");
                out.println("<td>" + board.getWriter() + "</td>");
                out.println("<td>" + board.getRegDate() + "</td>");
                out.println("<td>" + board.getCnt() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<br>");
            out.println("<a href='insertBoard.html'>새글 등록</a>");
            out.println("</center>");
            out.println("</body>");
            out.println("</html>");

            out.close();
        }
    }

}

