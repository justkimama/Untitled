package exServlet.servlet.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exServlet.model.board.BoardDAO;
import exServlet.model.board.BoardVO;

@WebServlet("/board_list.do")
public class BoardListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageSize = 10;

        String pageNum = request.getParameter("pageNum");
        if (pageNum == null) {
            pageNum = "1";
        }

        int currentPage = Integer.parseInt(pageNum);
        int startRow = (currentPage - 1) * pageSize + 1;
        int endRow = currentPage * pageSize;

        BoardDAO dao = BoardDAO.getInstance();
        int count = dao.boardCount();

        List<BoardVO> list = dao.boardListNew(startRow, endRow);

        request.setAttribute("list", list);
        request.setAttribute("count", count);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("startRow", startRow);
        request.setAttribute("endRow", endRow);

        RequestDispatcher rd = request.getRequestDispatcher("/Day_1006/Board/board_list.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}