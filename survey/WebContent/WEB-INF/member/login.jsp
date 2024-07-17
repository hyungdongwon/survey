<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../_include/inc_header.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name='loginform'>
	<table border='1' align='center' width='30%' height="200px" >
		<caption>로그인</caption>
		<tr>
			<td>아이디</td>
			<td><input type='text' name='member_id'></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type='password' name='member_password'></td>
		</tr>
		<tr>
			<td colspan='3' align='center'>
				<button type='button' onclick='check();'>로그인</button>
			</td>
		</tr>
	</table>
</form>
</body>
<script>
function check(){

	
	if(loginform.member_id.value.trim() =="" ){
		alert("아이디를 입력해주세요");
		loginform.member_id.focus();
		return false;
	}
	if(loginform.member_password.value.trim() =="" ){
		alert("비밀번호를 입력해주세요");
		loginform.member_password.focus();
		return false;
	}
	document.loginform.method='post';
	document.loginform.action="${path}/member/loginProc";
	document.loginform.submit();
}
</script>
</html>