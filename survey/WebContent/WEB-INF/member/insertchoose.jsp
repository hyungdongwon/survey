<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../_include/inc_header.jsp" %>

	
	<table border='1' align='center'>
		<tr>
			<td>
				<form name='form1' method='post' action="${path}/member/insert">
					<button type="submit" style="width:200px; height:150px;">일반회원 회원가입</button>
				</form>
			</td>
			<td>
				<form name='form2' method='post' action="${path}/member/insert">
					<input type='hidden' name="code" value="2">
					<button type="submit" style="width:200px; height:150px;">기업 회원가입</button>
				</form>
			</td>
		</tr>
	</table>
