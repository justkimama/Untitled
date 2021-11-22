package exServlet.servlet.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exServlet.model.member.MemberDAO;
import exServlet.model.member.MemberVO;
import exServlet.util.UserSHA256;

@WebServlet("/member_insert.do")
public class MemberInsertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd =
                request.getRequestDispatcher("/Day_1006/Member/member_insert.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String userid = request.getParameter("userid");
        String email = request.getParameter("email");
        String passwd = UserSHA256.getSHA256(request.getParameter("passwd"));

        MemberVO vo = new MemberVO();
        vo.setName(name);
        vo.setUserid(userid);
        vo.setEmail(email);
        vo.setPasswd(passwd);

        MemberDAO dao = MemberDAO.getInstance();
        int row = dao.memberWrite(vo);

        request.setAttribute("row", row);
        RequestDispatcher rd = request.getRequestDispatcher("/Day_1006/Member/member_insert_pro.jsp");
        rd.forward(request, response);
    }
}
