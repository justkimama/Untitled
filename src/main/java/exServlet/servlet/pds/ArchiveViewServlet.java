package exServlet.servlet.pds;

import exServlet.model.pds.PdsDAO;
import exServlet.model.pds.PdsVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/archive_view.do")
public class ArchiveViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idx = Integer.parseInt(request.getParameter("idx"));

        PdsDAO dao = PdsDAO.getInstance();
        PdsVO vo = dao.pdsViewNew(idx);
        request.setAttribute("vo", vo);
        RequestDispatcher rd = request.getRequestDispatcher("/Day_1006/Archive/archive_view.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}
