package exServlet.servlet.board;

import exServlet.model.board.BoardDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/board_delete.do")
public class BoardDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idx = Integer.parseInt(request.getParameter("idx"));
        BoardDAO dao = BoardDAO.getInstance();
        int row = dao.boardDeleteNew(idx);
        request.setAttribute("row", row);
        RequestDispatcher rd = request.getRequestDispatcher("/Day_1006/Board/board_delete_pro.jsp");
        rd.forward(request, response);
    }
}