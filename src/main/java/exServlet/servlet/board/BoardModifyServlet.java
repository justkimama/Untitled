package exServlet.servlet.board;

import exServlet.model.board.BoardDAO;
import exServlet.model.board.BoardVO;
import exServlet.model.member.MemberDAO;
import exServlet.model.member.MemberVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/board_modify.do")
public class BoardModifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idx = Integer.parseInt(request.getParameter("idx"));
        BoardDAO dao = BoardDAO.getInstance();
        BoardVO vo = dao.boardView(idx);

        request.setAttribute("vo", vo);

        RequestDispatcher rd = request.getRequestDispatcher("/Day_1006/Board/board_modify.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        BoardVO vo = new BoardVO();
        vo.setIdx(Integer.parseInt(request.getParameter("idx")));
        vo.setSubject(request.getParameter("subject"));
        vo.setContents(request.getParameter("contents"));
        vo.setUserid(request.getParameter("userid"));

        BoardDAO dao = BoardDAO.getInstance();
        int row = dao.boardModifyNew(vo);
        request.setAttribute("row", row);
        RequestDispatcher rd = request.getRequestDispatcher("/Day_1006/Board/board_modify_pro.jsp");
        rd.forward(request, response);
    }
}