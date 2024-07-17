<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form name='passwordupdate' method='post' action='${path}/member/passwordupdateProc'>
	<table border='1' align='center'>
	<caption>비밀번호 변경</caption>
		<tr>
			<td>변경하실 비밀번호</td>
			<td><input type='password' name='password'></td>
		</tr>
		<tr>
			<td>변경하실 비밀번호 확인</td>
			<td><input type='password' name='passwordcheck'></td>
		</tr>
		<tr>
			<td colspan='2' align='center'>
				<button type='button' onclick="check();">비밀번호 변경</button>
			</td>
		</tr>
	</table>
</form>

<script>
function check(){
	var f = document.passwordupdate;
	
	if(f.password.value == "" || f.password.value == null){
		alert("password를 입력해주세요");
		f.password.focus();
		return false;
	}
	if(f.passwordcheck.value == "" || f.passwordcheck.value == null){
		alert("비밀번호 확인을 입력해주세요");
		f.passwordcheck.focus();
		return false;
	}
	
	if(f.password.value != f.passwordcheck.value){
		alert("비밀번호 가 일치하지 않습니다.");
		f.password.focus();
		return false;
	}
	
	if(confirm("변경하시겠습니까?")){
		f.submit();	
	}
	
}

</script>
