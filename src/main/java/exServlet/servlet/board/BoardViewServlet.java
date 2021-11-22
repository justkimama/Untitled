package exServlet.servlet.board;

import exServlet.model.board.BoardDAO;
import exServlet.model.board.BoardVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/board_view.do")
public class BoardViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idx = Integer.parseInt(request.getParameter("idx"));

        BoardDAO dao = BoardDAO.getInstance();
//        //쿠키 검사 및 생성
//        boolean bool = false;
//        Cookie info = null;
//        //사용자의 컴퓨터에서 모든 쿠키정보 가져오기
//        Cookie[] cookies = request.getCookies();
//        for (int x = 0; x < cookies.length; x++) {
//            info = cookies[x];
//            if (info.getName().equals("Board" + idx)) {
//                bool = true;
//                break;
//            }
//        }
//        String newValue = "" + System.currentTimeMillis();
//        if (!bool) { //쿠키가 존재하지 않으면
//            info = new Cookie("Board" + idx, newValue);
//            info.setMaxAge(60 * 60);//쿠키유효시간(한시간)
//            response.addCookie(info);//쿠키정보 전송
//        }
        dao.boardHits(idx);
        BoardVO vo = dao.boardView(idx);

        request.setAttribute("vo", vo);
        RequestDispatcher rd = request.getRequestDispatcher("/Day_1006/Board/board_view.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
