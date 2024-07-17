<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../_include/inc_header.jsp" %>
<hr>
<div align='center'>회원관리</div>
<hr>
<ul style="line-height:500%; font-size:13px;" >
	<li>
		<a href="${path}/member/update">회원정보 수정</a><br>
	</li>
	<li>
		<a href="${path}/member/history">설문조사참여내역</a>
	</li>
	<li>
		<a href="${path}/member/accumulate">포인트 적립조회</a>
	</li>
	<li>
		<a href="${path}/member/delete">회원탈퇴</a>
	</li>
	<li>
		<a href="${path}/member/passwordupdate">비밀번호 변경</a>
	</li>
</ul>

	
	