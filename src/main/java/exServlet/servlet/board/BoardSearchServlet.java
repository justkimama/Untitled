package exServlet.servlet.board;

import exServlet.model.board.BoardDAO;
import exServlet.model.board.BoardVO;
import exServlet.util.DBManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/board_search.do")
public class BoardSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BoardDAO dao = BoardDAO.getInstance();
        int index;
        String search = request.getParameter("search");
        String text = request.getParameter("search_text");
        if(search.equals("subject")){
            index = 1;
        } else if(search.equals("contents")){
            index = 2;
        } else{
            index = 3;
        }
        List<BoardVO> list = dao.boardSearch(text, index);
        request.setAttribute("list", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Day_1006/Board/board_search.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
