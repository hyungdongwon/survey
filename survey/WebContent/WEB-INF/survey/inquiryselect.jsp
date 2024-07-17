<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../_include/inc_header.jsp" %>
<table border='1' align="center" style="width:85%; height:70%;">
<form name="form1" method="post">
	<caption style="font-weight: bolder;">1:1문의
		<div align='right'>
			${dto.inquiry_disclosure}
		</div>
		<div align='left'>작성자: ${dto.member_name}</div>
	</caption>
	<tr>
		<th style="width:20%; height:10%;">
			설문조사 유형<br>
		</th>
		<th>
			${dto.inquiry_type}
		</th>
	</tr>
	<tr>
		<th style="width:20%; height:10%;">
			문의제목
		</th>
		<th>${dto.inquiry_subject}</th>
	</tr>
	<tr>
		<th style="width:20%; height:40%;" colspan='3'>
			문의질문<br>
			<textarea rows="10" cols="70" readonly>${dto.inquiry_content}</textarea>
		</th>
	</tr>
	<tr>
		<th style="width:20%; height:40%;" colspan='3'>
			문의 답변<br>
			<textarea rows="10" cols="70" readonly>${dto.inquiry_answer}</textarea>
		</th>
	</tr>
	<c:if test="${member_level >= 2 || dto.member_id == sessionScope.member_id}">
	<tr>
		<th colspan='3' align='right'>
			<a href='javascript:void(0);' onclick="update();">글수정하기</a>&nbsp;&nbsp;
			<a href='javascript:void(0);' onclick="delete1();">글삭제하기</a>
			<input type='hidden' name="inquiry_no" value="${dto.inquiry_no}">
		</th>
	</tr>
	</c:if>
</form>
</table>
<script>
function update(){
	form1.action="${path}/survey/inquiryupdate";
	form1.submit();
}
function delete1(){
	if(confirm("글을 삭제하시겠습니까?")){
		form1.action="${path}/survey/inquirydelete";
		form1.submit();
	}
}
</script>

<c:if test="${member_level >= 2}">
<hr>
<table border="1"  align="center" style="width:85%; height:70%;">
	<caption style="font-weight: bolder;">답변하기</caption>
	<form name="form2" method='post' action="${path}/survey/inquiryanswer">
		<tr>
			<th style="width:20%; height:40%;" colspan='3'>
				문의 답변<br>
				<textarea rows="10" cols="70" name='inquiry_answer'></textarea>
			</th>
		</tr>
		<tr>
			<td align='right'>
				<input type='hidden' name="inquiry_no" value="${dto.inquiry_no}">
				<button type='submit'>답변하기</button>
			</td>
		</tr>
	</form>
</table>
</c:if>
