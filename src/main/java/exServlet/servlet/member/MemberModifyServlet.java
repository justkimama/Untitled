package exServlet.servlet.member;


import exServlet.model.board.BoardDAO;
import exServlet.model.board.BoardVO;
import exServlet.model.member.MemberDAO;
import exServlet.model.member.MemberVO;
import exServlet.model.pds.PdsDAO;
import exServlet.model.pds.PdsVO;
import exServlet.util.DBManager;
import exServlet.util.UserSHA256;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/member_modify.do")
public class MemberModifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MemberVO vo = (MemberVO) session.getAttribute("user");
        request.setAttribute("vo", vo);

        String userid = (String) session.getAttribute("userid");
        BoardDAO dao2 = BoardDAO.getInstance();
        List<BoardVO> list = dao2.boardSelectNew(userid);
        request.setAttribute("list", list);

        PdsDAO dao3 = PdsDAO.getInstance();
        List<PdsVO> list2 = dao3.pdsSelectNew(userid);
        request.setAttribute("list2", list2);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Day_1006/Member/member_modify.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        MemberVO vo = new MemberVO();
        vo.setUserid(request.getParameter("userid"));
        vo.setPasswd(UserSHA256.getSHA256(request.getParameter("password")));
        vo.setName(request.getParameter("name"));
        vo.setEmail(request.getParameter("email"));


        MemberDAO dao = MemberDAO.getInstance();
        dao.memberModifyNew(vo);

        int row = dao.memberLogin(vo.getUserid(), vo.getPasswd());
        if (row == 1) { //로그인 성공
            //세션객체 생성
            vo = dao.getMember(vo.getUserid());
            HttpSession session = request.getSession();//세션객체 생성
            session.setAttribute("user", vo);
            session.setAttribute("userid",vo.getUserid());
            session.setMaxInactiveInterval(1800);//세션유지시간
        }

        request.setAttribute("row", row);
        RequestDispatcher rd = request.getRequestDispatcher("/Day_1006/Member/member_modify_pro.jsp");
        rd.forward(request, response);
    }
}