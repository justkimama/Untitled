package exServlet.servlet.board;

import exServlet.model.board.BoardDAO;
import exServlet.model.board.BoardVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/board_write.do")
public class BoardWriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/Day_1006/Board/board_write.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String subject = request.getParameter("subject");
        String contents = request.getParameter("contents");
        String userid = request.getParameter("userid");

        BoardVO vo = new BoardVO();
        vo.setName(name);
        vo.setSubject(subject);
        vo.setContents(contents);
        vo.setUserid(userid);

        BoardDAO dao = BoardDAO.getInstance();
        int row = dao.boardWrite(vo);

        request.setAttribute("row", row);
        RequestDispatcher rd = request.getRequestDispatcher("/Day_1006/Board/board_write_pro.jsp");
        rd.forward(request, response);
    }
}
