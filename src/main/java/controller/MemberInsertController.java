package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import biz.MemberService;
import vo.MemberVO;

public class MemberInsertController implements Controller {
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		
		if(req.getMethod().equals("POST")) {
			return processInsertService(req, resp);
		} else if(req.getMethod().equals("GET")){
			return processInsertView(req, resp);
		}
		
		return "redirect::/";
	}
	
	public String processInsertService(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("인서트 컨트롤러 실행중");
		int custno = Integer.parseInt(req.getParameter("custno"));
		String custname = req.getParameter("custname");
		System.out.println(custname);
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		System.out.println("조인데이트 1");
		Date joindate = Date.valueOf(req.getParameter("joindate"))  ;
		System.out.println("조인데이트 2");
		String grade = req.getParameter("grade");
		String city = req.getParameter("city");
		
		MemberVO member = new MemberVO();
		member.setCustno(custno);
		member.setCustname(custname);
		member.setPhone(phone);
		member.setAddress(address);
		member.setJoindate(joindate);
		member.setGrade(grade);
		member.setCity(city);
		
		MemberService service = new MemberService();
		int n = 0;
		try {
			n = service.memberInsert(member);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(n > 0) {
			return "redirect::memberList";
		}else {
			return "memberInput";
		}

	}
	
	public String processInsertView(HttpServletRequest req, HttpServletResponse resp) {
		MemberService service = new MemberService();
		int custno = service.getMaxCustNo();
		req.setAttribute("custno", custno);
		
		return "memberInput";

	}

}
