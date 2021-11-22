package exServlet.model.pds;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import exServlet.model.board.BoardVO;
import exServlet.util.DBManager;

public class PdsDAO {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    private PdsDAO() {
    }

    private static PdsDAO instance = new PdsDAO();

    public static PdsDAO getInstance() {
        return instance;
    }

    public int pdsWriteNew(PdsVO vo) {
        String query = "insert into tbl_pds(idx,name,filename,userid) "
                + "values(tbl_pds_seq_idx.nextval,?,?,?)";
        int row = 0;
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, vo.getName());
//			pstmt.setString(2, vo.getContents());
            pstmt.setString(2, vo.getFilename());
            pstmt.setString(3, vo.getUserid());
            row = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return row;
    }

    public List<PdsVO> pdsListNew() {
        String query = "select idx,filename,TO_CHAR(regdate,'MM-DD') as reg,name,readcnt from tbl_pds order by idx desc";
        List<PdsVO> list = new ArrayList();
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                PdsVO vo = new PdsVO();
                vo.setIdx(rs.getInt("idx"));
                vo.setName(rs.getString("name"));
                vo.setRegdate(rs.getString("reg"));
                vo.setReadcnt(rs.getInt("readcnt"));
                vo.setFilename(rs.getString("filename"));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return list;
    }

    public void pdsDeleteFile(int idx) {
        String query = "select filename from tbl_pds where idx=?";
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idx);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                PdsVO vo = new PdsVO();
                vo.setFilename(rs.getString("filename"));
                File file = new File("C:/Users/kshom/WebUntitled/out/artifacts/WebUntitled_war_exploded/Day_1006/Archive/Storage/" + vo.getFilename());
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
    }

    public void pdsDeleteFileAll(String userid) {
        String query = "select filename from tbl_pds where userid=?";
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                PdsVO vo = new PdsVO();
                vo.setFilename(rs.getString("filename"));
                File file = new File("C:/Users/kshom/WebUntitled/out/artifacts/WebUntitled_war_exploded/Day_1006/Archive/Storage/" + vo.getFilename());
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
    }

    public int pdsDeleteAll(String usrid) {
        String query = "delete from tbl_pds where userid=?";
        int row = 0;
        try {

            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, usrid);
            row = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return row;
    }

    public int pdsDeleteNew(int idx) {
        String query = "delete from tbl_pds where idx=?";
        int row = 0;
        try {

            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idx);
            row = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return row;
    }

    //view 메소드
    public PdsVO pdsViewNew(int idx) {
        String query = "select * from tbl_pds where idx=?";
        PdsVO vo = new PdsVO();
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idx);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                vo.setUserid(rs.getString("userid"));
                vo.setIdx(rs.getInt("idx"));
                vo.setFilename(rs.getString("filename"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return vo;
    }

    public List<PdsVO> pdsSelectNew(String userid) {
        String query = "select idx,name,filename,TO_CHAR(regdate,'MM-DD') as reg from tbl_pds where userid = ? order by idx desc";
        List<PdsVO> list = new ArrayList();
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                PdsVO vo = new PdsVO();
                vo.setIdx(rs.getInt("idx"));
                vo.setName(rs.getString("name"));
                vo.setFilename(rs.getString("filename"));
                vo.setRegdate(rs.getString("reg"));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return list;
    }

    public List<PdsVO> pdsSearch(String text, int index) {
        String query = null;
        if (index == 1) {
            query = "select idx,name,filename,TO_CHAR(regdate,'MM-DD') as reg from tbl_pds where lower(userid) LIKE '%' || lower(?) || '%' order by idx desc";
        } else {
            query = "select idx,name,filename,TO_CHAR(regdate,'MM-DD') as reg from tbl_pds where lower(filename) LIKE '%' || lower(?) || '%' order by idx desc";
        }
        List<PdsVO> list = new ArrayList();
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, text);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                PdsVO vo = new PdsVO();
                vo.setIdx(rs.getInt("idx"));
                vo.setName(rs.getString("name"));
                vo.setFilename(rs.getString("filename"));
                vo.setRegdate(rs.getString("reg"));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return list;
    }

    public List<PdsVO> pdsListNew2(int startRow, int endRow) {
        String sql = "select * from "
                + "(select rownum rn, idx,filename,TO_CHAR(regdate,'MM-DD') as reg,name,readcnt from "
                + "(select * from tbl_pds order by idx desc)) where rn between ? and ?";

        List<PdsVO> list = new ArrayList<>();
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                do {
                    PdsVO vo = new PdsVO();
                    vo.setIdx(rs.getInt("idx"));
                    vo.setName(rs.getString("name"));
                    vo.setRegdate(rs.getString("reg"));
                    vo.setReadcnt(rs.getInt("readcnt"));
                    vo.setFilename(rs.getString("filename"));
                    list.add(vo);

                } while (rs.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return list;
    }


    public int pdsCount() {
        int count = 0;
        String sql = "select count(*) from tbl_pds";
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return count;
    }
}
