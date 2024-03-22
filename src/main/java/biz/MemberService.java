package biz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBUtils;
import dao.MemberDao;
import vo.MemberMoneyVO;
import vo.MemberVO;

public class MemberService {
	
	MemberDao dao = new MemberDao();
	
	public ArrayList<MemberVO> memberList() {
		ArrayList<MemberVO> list = dao.memberList();
		return list;
	}
	
	public ArrayList<MemberMoneyVO> memberMoneyList() {
		System.out.println("머니 서비스 옴");
		ArrayList<MemberMoneyVO> list = dao.memberMoneyList();
		System.out.println("머니 서비스 나감");
		return list;
	}
	
	public int getMaxCustNo() {
		int custno = dao.getMaxCustNo();
		return custno;
	}
	
	public int memberInsert(MemberVO member) throws SQLException {
		int n = dao.memberInsert(member);
		return n;
	}
	
	public MemberVO getMember(int custno) {
		MemberVO member = dao.getMember(custno);
		return member;
	}
	
	public int memberUpdate(MemberVO member) throws SQLException{
		int n =dao.memberUpdate(member);
		return n;
	}
	
	

}
