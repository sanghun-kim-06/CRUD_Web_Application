package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBUtils;
import vo.MemberMoneyVO;
import vo.MemberVO;

public class MemberDao {
	
	public ArrayList<MemberVO> memberList() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtils.getConnection();
			psmt = conn.prepareStatement("select custno, custname, phone, address, joindate, decode(grade, 'A', 'VIP', 'B', '일반', 'C', '직원') grade, city \r\n"
			+ " from MEMBER_TBL_02 order by custno");
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setCustno(rs.getInt("custno"));
				vo.setCustname(rs.getString("custname"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				vo.setJoindate(rs.getDate("joindate"));
				vo.setGrade(rs.getString("grade"));
				vo.setCity(rs.getString("city"));
				list.add(vo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, psmt, rs);
		}
		
		return list;
		
	}
	
	public ArrayList<MemberMoneyVO> memberMoneyList(){
		System.out.println("머니 DAO 옴");

		ArrayList<MemberMoneyVO> list = new ArrayList<MemberMoneyVO>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtils.getConnection();
			psmt = conn.prepareStatement("select A.custno, A.custname, decode(A.grade, 'A', 'VIP', 'B', '일반', 'C', '직원') grade, sum(B.PRICE) PRICE \r\n"
									  +" from MEMBER_TBL_02 A, MONEY_TBL_02 B \r\n "
									  +" WHERE A.custno = B.custno \r\n "
									  +" GROUP BY A.custno, A.custname, A.grade \r\n "
									  +" ORDER BY PRICE DESC ");
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				MemberMoneyVO vo = new MemberMoneyVO();
				vo.setCustno(rs.getInt("custno"));
				vo.setCustname(rs.getString("custname"));
				vo.setGrade(rs.getString("grade"));
				vo.setPrice(rs.getInt("Price"));
				list.add(vo);
				System.out.println("머니 DAO 실행");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("머니 DAO 나감");
			DBUtils.close(conn, psmt, rs);
		}
		
		return list;
	}
	
	public int getMaxCustNo() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int custno = 0;
		
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(" select max(custno) + 1 custno from MEMBER_TBL_02 ");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				custno = rs.getInt("custno");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, ps, rs);
		}
		
		return custno;
	}
	
	public int memberInsert(MemberVO member) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		int n = 0;
		
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(" insert into member_tbl_02 values(?, ?, ?, ?, ?, ?, ?) ");
			ps.setInt(1, member.getCustno());
			ps.setString(2, member.getCustname());
			ps.setString(3, member.getPhone());
			ps.setString(4, member.getAddress());
			ps.setDate(5, member.getJoindate());
			ps.setString(6, member.getGrade());
			ps.setString(7, member.getCity());
			n = ps.executeUpdate();
			if(n > 0) {
				conn.commit();
			}
		}catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			DBUtils.close(conn, ps);
		}
		
		return n;
	}
	
	public MemberVO getMember(int custno) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberVO vo = null;
		
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(" select custno, custname, phone, address, joindate, grade, city \r\n "
										+ " from MEMBER_TBL_02 WHERE custno = ? ");
			ps.setInt(1, custno);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				vo = new MemberVO();
				vo.setCustno(rs.getInt("custno"));
				vo.setCustname(rs.getString("custname"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				vo.setJoindate(rs.getDate("joindate"));
				vo.setGrade(rs.getString("grade"));
				vo.setCity(rs.getString("city"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, ps, rs);
		}
		return vo;
		
	}
	
	public int memberUpdate(MemberVO member) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		int n = 0;
		
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(" Update member_tbl_02 set custname = ?, phone = ?, address = ?, joindate = ?, grade = ?, city = ? "
									+  " where custno = ? ");
			
			ps.setString(1, member.getCustname());
			ps.setString(2, member.getPhone());
			ps.setString(3, member.getAddress());
			ps.setDate(4, member.getJoindate());
			ps.setString(5, member.getGrade());
			ps.setString(6, member.getCity());
			ps.setInt(7, member.getCustno());
			n = ps.executeUpdate();
			if(n > 0) {
				conn.commit();
			}
		}catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			DBUtils.close(conn, ps);
		}
		
		return n;
	}

}
