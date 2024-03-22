<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="master/header.jsp"></jsp:include>

<section>
<div class="container">
	<p class="title">홈쇼핑 회원 수정</p>
	<% MemberVO member = (MemberVO) request.getAttribute("member"); %>
	<form action="/memberUpdate" method="post" name="frm">
		<table width="600px">
			<tr>
				<td>회원번호(자동발생)</td>
				<td><input type="number" value="${member.custno }" name="custno" id="custno" readonly></td>
			</tr>
			<tr>
				<td>회원성명</td>
				<td><input type="text" value="${member.custname }" name="custname" id="custname"></td>
			</tr>
			<tr>
				<td>회원전화</td>
				<td><input type="text" name="phone" id="phone" value="${member.phone }"></td>
			</tr>
			<tr>
				<td>회원주소</td>
				<td><input type="text" name="address" id="address" value="${member.address }"></td>
			</tr>
			<tr>
				<td>가입일자</td>
				<td><input type="date" name="joindate" id="joindate" value="${member.joindate }"></td>
			</tr>
			<tr>
				<td>고객등급[A: VIP, B: 일반, C: 직원]</td>
				<td><select id="grade" name="grade">
					<option value="A" <%="A".equals(member.getGrade())?"selected":"" %>>VIP</option>
					<option value="B" <%="B".equals(member.getGrade())?"selected":"" %>>일반</option>
					<option value="C" <%="C".equals(member.getGrade())?"selected":"" %>>직원</option>
				</select></td>
			</tr>
			<tr>
				<td>도시코드</td>
				<td><input type="text" name="city" id="city" value="${member.city }"></td>
			</tr>
			
			<tr colspan="2" align="center">
				<input type="submit" value="수정하기" onclick="return checkForm()">
			</tr>
		</table>
	</form>
</div>
</section>
<script>
function checkForm(){
		if(document.frm.custname.value.trim() == ""){
			alert("회원성명을 입력하세요.");
			document.frm.custname.focus();
			return false;
		}
		if(document.frm.phone.value.trim() == ""){
			alert("회원전화를 입력하세요.");
			document.frm.phone.focus();
			return false;
		}
		if(document.frm.address.value.trim() == ""){
			alert("회원주소를 입력하세요.");
			document.frm.address.focus();
			return false;
		}
		if(document.frm.joindate.value.trim() == ""){
			alert("가입일자를 입력하세요.");
			document.frm.address.focus();
			return false;
		}
		if(document.frm.grade.value == ""){
			alert("고객등급을 입력하세요");
			document.frm.grade.focus();
			return false;
		}
		if(document.frm.city.value.trim() == ""){
			alert("도시코드를 입력하세요.");
			document.frm.city.focus();
			return false;
		}
		document.frm.submit();
	}
</script>

<jsp:include page="master/footer.jsp"></jsp:include>