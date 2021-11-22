package exServlet.model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import exServlet.model.board.BoardVO;
import exServlet.util.DBManager;

public class MemberDAO {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    private MemberDAO() {
    }

    private static MemberDAO instance = new MemberDAO();

    public static MemberDAO getInstance() {
        return instance;
    }

    public int memberIDcheck(String userid) {

        String query = "select count(*) as counter  from tbl_member where userid=?";
        int row = 0;
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return row;
    }

    public int memberWrite(MemberVO vo) {
        String query = "insert into tbl_member(name,userid,passwd,email) values (?,?,?,?)";
        int row = 0;
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getUserid());
            pstmt.setString(3, vo.getPasswd());
            pstmt.setString(4, vo.getEmail());

            row = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return row;
    }

    public int memberLogin(String userid, String passwd) {
        String query = "select passwd from tbl_member where userid=?";
        int row = 0;
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String dbpass = rs.getString("passwd");
                if (dbpass.equals(passwd)) {//로그인 성공
                    query = "update tbl_member set list_time=sysdate where userid=?";
                    pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, userid);
                    row = pstmt.executeUpdate();
                } else {//비번오류
                    row = 0;
                }
            } else {//아이디가 존재하지 않을 경우
                row = -1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return row;
    }

    public MemberVO getMember(String userid) {
        String query = "select * from tbl_member where userid=?";
        MemberVO vo = new MemberVO();
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setNString(1, userid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                vo.setName(rs.getString("name"));
                vo.setUserid(rs.getString("userid"));
                vo.setEmail(rs.getString("email"));
                vo.setPasswd(rs.getString("passwd"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return vo;
    }

    public int memberModifyNew(MemberVO vo) {
        String query = "update tbl_member set name=?, passwd=?, email=? "
                + " where userid=?";
        int row = 0;
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getPasswd());
            pstmt.setString(3, vo.getEmail());
            pstmt.setString(4, vo.getUserid());

            row = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return row;
    }

    public int memberDeleteNew(String userid) {
        String query = "delete from tbl_member where userid=?";
        int row = 0;
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userid);

            row = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return row;
    }
}
