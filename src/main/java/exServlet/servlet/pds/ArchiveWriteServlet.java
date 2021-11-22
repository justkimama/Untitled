package exServlet.servlet.pds;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import exServlet.model.pds.PdsDAO;
import exServlet.model.pds.PdsVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/archive_write.do")
public class ArchiveWriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/Day_1006/Archive/archive_write.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ServletContext context = getServletContext();
        String path=context.getRealPath("Day_1006/Archive/Storage");//파일업로드 경로
        String encType="UTF-8";
        int sizeLimit = 2*1024*1024;//파일최대 사이즈 2M
        MultipartRequest multi=new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());

        String name=multi.getParameter("name");
        String subject=multi.getParameter("subject");
//        String contents=multi.getParameter("contents");
        String userid = multi.getParameter("userid");

        String filename=multi.getFilesystemName("filename");

        PdsDAO dao = PdsDAO.getInstance();
        PdsVO vo = new PdsVO();
        vo.setName(name);
        vo.setSubject(subject);
//        vo.setContents(contents);
        vo.setUserid(userid);
        vo.setFilename(filename);

        int row = dao.pdsWriteNew(vo);

        request.setAttribute("row", row);

        RequestDispatcher rd = request.getRequestDispatcher("/Day_1006/Archive/archive_write_pro.jsp");
        rd.forward(request, response);
    }
}
