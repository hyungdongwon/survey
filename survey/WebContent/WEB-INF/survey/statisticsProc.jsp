<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file = "../_include/inc_header.jsp" %>
<form name='form1' method='post' action="participationProc">
	<table border='1' align='center' width='60%' height='400px'>
		 <caption style="font-weight: bolder;">설문 통합 통계<div align='right'>참여인원: ${total_user }</caption>
		
		 <tr height="13%;"style="font-weight: bolder;" align='center'>
		     <td colspan='2'>제목<br>
		     	${dto.survey_name }
		     </td>
		 </tr>
		 <tr>
		     <td colspan='2' style="font-weight: bolder;" align='center'>설문설명<br>
		     	${dto.survey_content }
		     </td>
		 </tr>
		
		 <c:forEach var='i' items="${list}" varStatus="status">
		 <tr>
		 	<td align='center'>
		 		질문:${i.question_no}<br>
		 		<c:forEach var='j' items="${list2[status.index]}" varStatus="jStatus">
		 			<c:forEach var="k" items="${j}" varStatus="kStatus" >
		 			<c:set var="num" value="${num+1}" />		 			
		 			<c:set var="key">${num-1}</c:set>
					<c:set var="val">${map[key]}</c:set>
		 				${k}: ${val}%<br>
		 			</c:forEach>
		 		</c:forEach>
		 	</td>
		 </tr>
		 </c:forEach>
		 <tr>
		 	<td align='right'>
		 		<button type='submit'>설문조사 완료</button>
		 	</td>
		 </tr>
	</table>
</form>