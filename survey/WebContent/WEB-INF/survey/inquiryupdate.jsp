<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../_include/inc_header.jsp" %>
<table border='1' align="center" style="width:85%; height:70%;">
<form name="form1" method="post" action="${path}/survey/inquiryupdateProc">
	<caption style="font-weight: bolder;">1:1문의 수정</caption>
	<tr>
		<th style="width:90%; height:20%;">
			<div align='right'>비공개
				<c:choose>
					<c:when test="${dto.inquiry_disclosure == '비공개'}">
						<input type='checkbox' name="inquiry_disclosure" value="비공개" checked><br>
					</c:when>
					<c:otherwise>
						<input type='checkbox' name="inquiry_disclosure" value="비공개"><br>
					</c:otherwise>
				</c:choose>
			</div>
			1:1문의 유형<br>
			<select name="inquiry_type" style="width:20%;">
				<c:choose>
					<c:when test="${dto.inquiry_type == '설문조사'}">
						<option value="설문조사" selected>설문조사</option>
					</c:when>
					<c:otherwise>
						<option value="설문조사">설문조사</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${dto.inquiry_type eq '회원'}">
						<option value="회원" selected>회원</option>
					</c:when>
					<c:otherwise>
						<option value="회원">회원</option>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${dto.inquiry_type eq '기타'}">
						<option value="기타" selected>기타</option>
					</c:when>
					<c:otherwise>
						<option value="기타">기타</option>
					</c:otherwise>
				</c:choose>
			</select><br>
		</th>
	</tr>
	<tr>
		<th>
			문의제목<br>
			<input type="text" name="inquiry_subject" style="width:35%;" value="${dto.inquiry_subject }"/><br><br>
			문의질문<br>
			<textarea name="inquiry_content" style="width:55%; height:45%;">${dto.inquiry_content}</textarea>
		</th>
	</tr>
	<tr>
		<td>
			<div align="center"><button type='submit' style="width:20%;">수정완료</button></div>
			<input type='hidden' name="inquiry_no" value="${dto.inquiry_no}">
		</td>
	</tr>
</form>
</table>