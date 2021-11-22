package exServlet.model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import exServlet.util.DBManager;

public class BoardDAO {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    private BoardDAO() {
    }

    private static BoardDAO instance = new BoardDAO();

    public static BoardDAO getInstance() {
        return instance;
    }

    // 게시물 전체 목록 구하기(검색X, 페이지처리 X)
    public List<BoardVO> boardList() {
        // query 생성
        String query = "select idx,name,subject,TO_CHAR(regdate,'MM-DD') as reg,readcnt from tbl_board order by idx desc";
        //리턴타입
        List<BoardVO> list = new ArrayList();
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setIdx(rs.getInt("idx"));
                vo.setName(rs.getString("name"));
//				vo.setEmail(rs.getString("email"));
                vo.setSubject(rs.getString("subject"));
                //vo.setContents(rs.getString("contents"));
                vo.setRegdate(rs.getString("reg"));
                vo.setReadcnt(rs.getInt("readcnt"));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return list;
    }

    // 글 조회수 증가 메소드
    public void boardHits(int idx) {
        String query = "update tbl_board set readcnt=readcnt+1 where idx=?";

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idx);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
    }

    public int boardWrite(BoardVO vo) {
        String query = "insert into tbl_board(idx,name,subject,contents,userid) values (tbl_board_seq_idx.nextval,?,?,?,?)";
        int row = 0;
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getSubject());
            pstmt.setString(3, vo.getContents());
            pstmt.setString(4, vo.getUserid());

            row = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return row;
    }

    //방명록 글 상세보기
    public BoardVO boardView(int idx) {
        String query = "select * from tbl_board where idx=?";
        BoardVO vo = new BoardVO();
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idx);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                vo.setIdx(rs.getInt("idx"));
                vo.setName(rs.getString("name"));
                vo.setSubject(rs.getString("subject"));
                vo.setContents(rs.getString("contents"));
                vo.setRegdate(rs.getString("regdate"));
                vo.setReadcnt(rs.getInt("readcnt"));
                vo.setUserid(rs.getString("userid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return vo;
    }

    public int boardModifyNew(BoardVO vo) {
        String query = "update tbl_board set subject=?, contents=? "
                + " where idx=? and userid=?";
        int row = 0;
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, vo.getSubject());
            pstmt.setString(2, vo.getContents());
            pstmt.setInt(3, vo.getIdx());
            pstmt.setString(4, vo.getUserid());

            row = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return row;
    }

    public int boardDeleteAll(String userid) {
        String query = "delete from tbl_board where userid=?";
        int row = 0;
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,userid);

            row = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return row;
    }

    public int boardDeleteNew(int idx) {
        String query = "delete from tbl_board where idx=?";
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

    // 게시물 전체 목록 구하기(검색X, 페이지처리 X)
    public List<BoardVO> boardSelectNew(String userid) {
        // query 생성
        String query = "select idx,name,subject,TO_CHAR(regdate,'MM-DD') as reg,readcnt from tbl_board where userid = ? order by idx desc";
        //리턴타입
        List<BoardVO> list = new ArrayList();
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setIdx(rs.getInt("idx"));
                vo.setName(rs.getString("name"));
                vo.setSubject(rs.getString("subject"));
                vo.setRegdate(rs.getString("reg"));
                vo.setReadcnt(rs.getInt("readcnt"));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return list;
    }

    public List<BoardVO> boardSearch(String text, int index) {
        String query = null;
        if (index == 1) {
            query = "select idx,name,subject,TO_CHAR(regdate,'MM-DD') as reg,readcnt from tbl_board where lower(subject) LIKE '%' || lower(?) || '%' order by idx desc";
        } else if (index == 2) {
            query = "select idx,name,subject,TO_CHAR(regdate,'MM-DD') as reg,readcnt from tbl_board where lower(contents) LIKE '%' || lower(?) || '%' order by idx desc";
        } else {
            query = "select idx,name,subject,TO_CHAR(regdate,'MM-DD') as reg,readcnt from tbl_board where lower(userid) LIKE '%' || lower(?) || '%' order by idx desc";
        }
        List<BoardVO> list = new ArrayList();
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,text);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setIdx(rs.getInt("idx"));
                vo.setName(rs.getString("name"));
                vo.setSubject(rs.getString("subject"));
                vo.setRegdate(rs.getString("reg"));
                vo.setReadcnt(rs.getInt("readcnt"));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return list;
    }

    public List<BoardVO> boardListNew(int startRow, int endRow) {
        String sql = "select * from "
                + "(select rownum rn, idx,name,subject,TO_CHAR(regdate,'MM-DD') as reg,readcnt from "
                + "(select * from tbl_board order by idx desc)) where rn between ? and ?";

        List<BoardVO> list = new ArrayList<>();
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                do {
                    BoardVO vo = new BoardVO();
                    vo.setIdx(rs.getInt("idx"));
                    vo.setName(rs.getString("name"));
                    vo.setSubject(rs.getString("subject"));
                    vo.setRegdate(rs.getString("reg"));
                    vo.setReadcnt(rs.getInt("readcnt"));

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

    public int boardCount() {
        int count = 0;
        String sql = "select count(*) from tbl_board";
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
