package exServlet.servlet.pds;

import exServlet.model.pds.PdsDAO;
import exServlet.model.pds.PdsVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/archive_search.do")
public class ArchiveSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PdsDAO dao = PdsDAO.getInstance();
        int index;
        String search = request.getParameter("search");
        String text = request.getParameter("search_text");
        if (search.equals("userid")) {
            index = 1;
        } else {
            index = 2;
        }
        List<PdsVO> list = dao.pdsSearch(text, index);
        request.setAttribute("list", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Day_1006/Archive/archive_search.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
