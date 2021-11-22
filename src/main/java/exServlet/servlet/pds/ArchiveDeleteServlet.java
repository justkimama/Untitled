package exServlet.servlet.pds;

import exServlet.model.pds.PdsDAO;
import exServlet.model.pds.PdsVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet("/archive_delete.do")
public class ArchiveDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idx = Integer.parseInt(request.getParameter("idx"));

        PdsDAO dao = PdsDAO.getInstance();
        dao.pdsDeleteFile(idx);
        int row = dao.pdsDeleteNew(idx);
        request.setAttribute("row", row);
        RequestDispatcher rd = request.getRequestDispatcher("/Day_1006/Archive/archive_delete_pro.jsp");
        rd.forward(request, response);
    }
}