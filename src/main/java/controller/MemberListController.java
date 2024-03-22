package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import biz.MemberService;
import vo.MemberVO;

public class MemberListController implements Controller{
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		MemberService service = new MemberService();
		ArrayList<MemberVO> list = service.memberList();
		
		req.setAttribute("list", list);
		
		return "memberList";
	}

}
