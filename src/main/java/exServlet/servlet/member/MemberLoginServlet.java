package exServlet.servlet.member;

import exServlet.model.member.MemberDAO;
import exServlet.model.member.MemberVO;
import exServlet.util.UserSHA256;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/member_login.do")
public class MemberLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/Day_1006/Member/member_login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberDAO dao = MemberDAO.getInstance();

        String userid = request.getParameter("userid");
        String passwd = UserSHA256.getSHA256(request.getParameter("passwd"));

        int row = dao.memberLogin(userid, passwd);
        if (row == 1) { //로그인 성공
            //세션객체 생성
            MemberVO vo = dao.getMember(userid);
            HttpSession session = request.getSession();//세션객체 생성
            session.setAttribute("user", vo);
            session.setAttribute("userid",vo.getUserid());
            session.setMaxInactiveInterval(1800);//세션유지시간
        }

        request.setAttribute("row", row);//1로그인 성공, 0비번오류, -1아이디 없음

        RequestDispatcher rd = request.getRequestDispatcher("/Day_1006/Member/member_login_ok.jsp");
        rd.forward(request, response);
    }
}