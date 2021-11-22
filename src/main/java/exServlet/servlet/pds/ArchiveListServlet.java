package exServlet.servlet.pds;

import exServlet.model.pds.PdsDAO;
import exServlet.model.pds.PdsVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/archive_list.do")
public class ArchiveListServlet extends HttpServlet {
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

        PdsDAO dao = PdsDAO.getInstance();
        int count = dao.pdsCount();

        List<PdsVO> list = dao.pdsListNew2(startRow, endRow);

        request.setAttribute("list", list);
        request.setAttribute("count", count);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("startRow", startRow);
        request.setAttribute("endRow", endRow);

        RequestDispatcher rd = request.getRequestDispatcher("/Day_1006/Archive/archive_list.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
