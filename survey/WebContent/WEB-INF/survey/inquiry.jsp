<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<table border='1' align="center" style="width:85%; height:70%;">
<form name="form1" method="post" action="${path}/survey/inquiryProc">
	<caption style="font-weight: bolder;">1:1문의</caption>
	
	<tr>
		
		<th style="width:90%; height:20%;">
			<div align='right'>비공개
				<input type='checkbox' name="inquiry_disclosure" value="비공개"><br>
			</div>
			1:1문의 유형<br>
			<select name="inquiry_type" style="width:20%;">
				<option value="설문조사">설문조사</option>
				<option value="회원">회원</option>
				<option value="기타">기타</option>
			</select><br>
			
		</th>
	</tr>
	<tr>
		<th>
			문의제목<br>
			<input type="text" name="inquiry_subject" style="width:35%;"/><br><br>
			문의질문<br>
			<textarea name="inquiry_content" style="width:55%; height:45%;" ></textarea>
		</th>
	</tr>
	<tr>
		<td>
			<div align="center"><button type='submit' style="width:20%;">작성완료</button></div>
		</td>
	</tr>
</form>
</table>
