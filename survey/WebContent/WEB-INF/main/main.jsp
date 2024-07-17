<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../_include/inc_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문조사</title>
</head>

<body>
<div align='right'>
	<c:choose>
		<c:when test="${sessionScope.member_id == null}">
			<a href="${path}/member/login">로그인</a>&nbsp;&nbsp;
			<a href="${path}/member/insertchoose">회원가입</a>&nbsp;&nbsp;
			<a href="${path}/member/select">마이페이지</a>
		</c:when>
		<c:otherwise>
			${sessionScope.member_name}님 환영합니다.&nbsp;&nbsp;
			<a href="${path}/member/select">마이페이지</a>&nbsp;&nbsp;
			<a href="${path}/member/logout">로그아웃</a>
		</c:otherwise>
	</c:choose>
</div>	
<hr>
	<table border='1' align='center' width="60%" height='900px;'>
		<tr>
			<td colspan='5' height='10%;' >&nbsp;&nbsp;
				<a href="${path}">메인페이지</a>
				<form name='searchform' method='post' action="${path}/survey/list"> 
					<div align='center' >
						<select name='searchOption' style="width:100px; height:32px;">
						<option value='survey_name'>제목</option>
						<option value='survey_content'>내용</option>
						<option value='survey_name_content'>제목+내용</option>
						<option value='member_name'>작성자</option>
						</select>
						<input type="text" name="searchData" style="text-align:center; width:200px; height:25px; letter-spacing: 0px;" placeholder="설문조사 검색">
						<button type='submit' style="width:50px; height:32px;">검색</button>
					</div>
				</form>
			</td>
		</tr>
		<tr>
			<td width="15%" valign="top">
				<side>
					<c:if test="${urifilename2 eq 'member'}">
						
					</c:if>
					<c:choose>
						<c:when test="${urifilename2 eq 'member'}">
							<jsp:include page = "../_include/inc_member_menu.jsp"/>
						</c:when>
						<c:otherwise>
							<jsp:include page ="../_include/inc_menu.jsp"/>
						</c:otherwise>
					</c:choose>
				</side>
			</td>
			<td valign="top">
				<c:if test="${urifilename2 != null}">
					<jsp:include page = "../${urifilename2 }/${urifilename }.jsp"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan='3' height="10%" >설문조사 사이트</td>
		</tr>
	</table>


</body>
</html>