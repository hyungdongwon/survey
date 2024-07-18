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
		<tr>
			<th>
				<table border='1' align='center' width='90%'>
					<caption>성별참여 현황</caption>
					<tr>
						<th>성별</th>
						<th>응답자수</th>
						<th>비율%</th>
					</tr>
					<tr>
						<th>남자</th>
						<th>${mentotal }명</th>
						<th>${mentotal2 }%</th>
					</tr>
					<tr>
						<th>여자</th>
						<th>${womentotal }명</th>
						<th>${womentotal2 }%</th>
					</tr>
					<tr>
						<th>계</th>
						<th> ${total_user }명</th>
						<th>100%</th>
					</tr>
				</table>
			</th>
		</tr>
		<tr>
			<th>
				<table border='1' align='center' width='90%'>
					<caption>연령대별 현황</caption>
					<tr>
						<th>연령대</th>
						<th>응답자수</th>
						<th>비율%</th>
					</tr>
					<c:forEach var='age' items="${agelist}"> 
						<tr>
							<th>${age.member_age1 }0대</th>
							<th>${age.age_count }명</th>
							<th>${age.age_count2}%</th>
						</tr>
					</c:forEach>
					<tr>
						<th>계</th>
						<th> ${total_user }명</th>
						<th>100%</th>
					</tr>
				</table>
			</th>
		</tr>
		 <c:forEach var='i' items="${list}" varStatus="status">
		 <tr>
		 	<th align='center'>
		 		질문:${i.question_no}<br>
		 		<c:forEach var='j' items="${list2[status.index]}" varStatus="jStatus">
		 			<c:forEach var="k" items="${j}" varStatus="kStatus" >
		 			<c:set var="num" value="${num+1}" />		 			
		 			<c:set var="key">${num-1}</c:set>
					<c:set var="val">${map[key]}</c:set>
		 				${k}: ${val}%<br>
		 			</c:forEach>
		 		</c:forEach>
		 	</th>
		 </tr>
		 </c:forEach>
	</table>
</form>