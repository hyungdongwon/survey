<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../_include/inc_header.jsp" %>

<table border='1' align='center'>
	<caption>설문조사 통계</caption>
	<tr>
		<th>설문조사 제목</th>
		<th>참여포인트</th>
		<th>시작일자</th>
		<th>종료일자</th>
		<th>통계보기</th>
	</tr>
	<c:forEach var='dto' items="${list}">
		<tr>
			<form name='listform' method='post' action="${path}/survey/statisticsProc">
				<td>${dto.survey_name}</td>
				<td>${dto.survey_point }</td>
				<td>${dto.survey_date }</td>
				<td>${dto.survey_end }</td>
				<input type='hidden' name='survey_no' value="${dto.survey_no}"/>
				<td><button type='submit'>통계보기</button></td>
			</form>
		</tr>
	</c:forEach>
	<tr>
		<td colspan='6' align='center'>
			<c:choose>
				<c:when test="${page != 1}">
					<a href="${path}/survey/statistics?page=1">[처음]</a>
				</c:when>
				<c:otherwise>
					[처음]
				</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${group != 1}">
					<a href="${path}/survey/statistics?page=${imsigroup-pagesize}">[이전]</a>
				</c:when>
				<c:otherwise>
					[이전]
				</c:otherwise>
			</c:choose>
		
			<c:set var="chk" value='true'/>
			<c:forEach var="i" begin="${imsigroup}" end="${maxpage}">
				<c:if test="${chk}">
					<c:if test="${i == group * pagesize}">
						 <c:set var="chk" value="false" />
					</c:if>
					<c:if test="${i == page }">
						[${i}]
					</c:if>
					<c:if test="${i != page }">
						<a href="${path}/survey/statistics?page=${i}">[${i}]</a>
					</c:if>
				</c:if>
			</c:forEach>
			
			<c:choose>
				<c:when test="${group != totalgroup && totalgroup != 1}">
					<a href="${path}/survey/statistics?page=${imsigroup+pagesize}">[다음]</a>
				</c:when>
				<c:otherwise>
					[다음]
				</c:otherwise>
			</c:choose>
			
			<c:if test="${page != maxpage}">
				<a href="${path}/survey/statistics?page=${maxpage}">[끝]</a>
			</c:if>
		</td>
	</tr>
</table>