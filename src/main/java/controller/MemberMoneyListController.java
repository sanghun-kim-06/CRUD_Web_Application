package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import biz.MemberService;
import vo.MemberMoneyVO;

public class MemberMoneyListController implements Controller{
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		System.out.println("머니 컨트롤러 옴");
		MemberService service = new MemberService();
		ArrayList<MemberMoneyVO> list = service.memberMoneyList();
		
		req.setAttribute("list", list);
		System.out.println("머니 컨트롤러 나감");
		return "memberMoneyList";
	}

}
