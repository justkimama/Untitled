package exServlet.servlet.member;

import exServlet.model.board.BoardDAO;
import exServlet.model.member.MemberDAO;
import exServlet.model.pds.PdsDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/member_delete.do")
public class MemberDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("userid");

        BoardDAO dao = BoardDAO.getInstance();
        dao.boardDeleteAll(userid);

        PdsDAO dao1 = PdsDAO.getInstance();
        dao1.pdsDeleteFileAll(userid);
        dao1.pdsDeleteAll(userid);

        MemberDAO dao2 = MemberDAO.getInstance();
        int row = dao2.memberDeleteNew(userid);
        request.setAttribute("row", row);
        RequestDispatcher rd = request.getRequestDispatcher("/Day_1006/Member/member_delete_pro.jsp");
        rd.forward(request, response);
    }
}