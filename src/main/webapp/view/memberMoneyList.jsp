<%@page import="vo.MemberMoneyVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="master/header.jsp"></jsp:include>
<section>
<div class="container">
	<p class="title">회원매출조회</p>
<%
	ArrayList<MemberMoneyVO> list = (ArrayList<MemberMoneyVO>)request.getAttribute("list");
	if(!list.isEmpty()){
%>
		<table>
			<tr>
				<td>회원번호</td>
				<td>회원성명</td>
				<td>고객등급</td>
				<td>매출</td>
			</tr>
<%
		for(MemberMoneyVO member : list){
			request.setAttribute("member", member);
%>
			<tr>
				<td>${member.custno } </td>
				<td>${member.custname } </td>
				<td>${member.grade } </td>
				<td>${member.price } </td>
			</tr>	
<%
		}
%>	
		</table>
<%
	}else {
		%>
		<p style="text-align: center">등록된 매출 정보가 없습ㄴ디ㅏ</p>
		<%
	}

%>
</div>
</section>
<jsp:include page="master/footer.jsp"></jsp:include>