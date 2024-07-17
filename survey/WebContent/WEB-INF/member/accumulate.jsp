<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../_include/inc_header.jsp" %>



<table border='1' align='center' width="80%">
	<caption>적립포인트 조회</caption>
	<tr>
		<th>구분</th>
		<th>적립포인트</th>
		<th>발생내역</th>
		<th>발생일자</th>
	</tr>
	<c:forEach var='dto' items="${list}">
		<tr>
			<td>적립</td>
			<td>+ ${dto.survey_point_getpoint }</td>
			<td>설문참여[${dto.survey_name}]</td>
			<td>${dto.survey_point_getdate }</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan='4' align='center'>
			<c:choose>
				<c:when test="${page != 1}">
					<a href="${path}/member/accumulate?page=1">[처음]</a>
				</c:when>
				<c:otherwise>
					[처음]
				</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${group != 1}">
					<a href="${path}/member/accumulate?page=${imsigroup-pagesize}">[이전]</a>
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
						<a href="${path}/member/accumulate?page=${i}">[${i}]</a>
					</c:if>
				</c:if>
			</c:forEach>
			
			<c:choose>
				<c:when test="${group != totalgroup && totalgroup != 1}">
					<a href="${path}/member/accumulate?page=${imsigroup+pagesize}">[다음]</a>
				</c:when>
				<c:otherwise>
					[다음]
				</c:otherwise>
			</c:choose>
			
			<c:if test="${page != maxpage}">
				<a href="${path}/member/accumulate?page=${maxpage}">[끝]</a>
			</c:if>
		</td>
	</tr>
</table><br><br>

<table border='1' align='center'  width="50%">
	<tr>
		<th>총적립금</th>
		<td>${sumpoint}&nbsp;POINT</td>
	</tr>
</table>
