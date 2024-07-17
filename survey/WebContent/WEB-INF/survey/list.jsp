<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../_include/inc_header.jsp" %>

<table border='1' align='center'>
	<caption>설문조사 목록</caption>
		<tr>
			<td>
				<table border='1' width="250px" height="250px" style="  border-spacing: 30px; border-collapse: separate;">
					<tr >
						<c:forEach var='dto' items="${list}" varStatus="status">
						<c:if test="${status.index % 3 ==0 }">
							<tr></tr>
						</c:if>
						<th valign="top">
						<form name='listform' method='post' action="${path}/survey/participation">
							 - ${dto.survey_name}<br>
							 참여POINT: ${dto.survey_point }<br><br><br><br><br>
							 설문기간:<br> <fmt:parseDate value="${dto.survey_date}" var="registered" pattern="yyyy-MM-dd HH:mm:ss" />
									<fmt:formatDate value="${registered}" pattern="yyyy-MM-dd" />~
									<fmt:parseDate value="${dto.survey_end}" var="registered" pattern="yyyy-MM-dd HH:mm:ss" />
									<fmt:formatDate value="${registered}" pattern="yyyy-MM-dd" /><br><br>
									<input type='hidden' name='survey_no' value="${dto.survey_no}"/>
									<input type='hidden' name="survey_point" value="${dto.survey_point}"/>
									<c:set var="chk" value="0" />
									
									<c:forEach var="i" items="${list2}">
											<c:if test="${i.survey_no != dto.survey_no}">
												<c:set var="chk" value="${chk+1}" />												
											</c:if>
									</c:forEach>
									<c:choose>
										<c:when test="${chk == list2size}">
											<button type='submit' style="width:230px;">참여하기</button>
										</c:when>
										<c:otherwise>
											<div style="width:230px;">[참여완료]</div>
										</c:otherwise>
									</c:choose>
						</form>	
						</c:forEach>	
						</th>
					</tr>
				</table>
			</td>
		</tr>
	
	<tr>
		<td colspan='4' align='center'>
			<c:choose>
				<c:when test="${page != 1}">
					<a href="${path}/survey/list?page=1">[처음]</a>
				</c:when>
				<c:otherwise>
					[처음]
				</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${group != 1}">
					<a href="${path}/survey/list?page=${imsigroup-pagesize}">[이전]</a>
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
						<a href="${path}/survey/list?page=${i}">[${i}]</a>
					</c:if>
				</c:if>
			</c:forEach>
			
			<c:choose>
				<c:when test="${group != totalgroup && totalgroup != 1}">
					<a href="${path}/survey/list?page=${imsigroup+pagesize}">[다음]</a>
				</c:when>
				<c:otherwise>
					[다음]
				</c:otherwise>
			</c:choose>
			
			<c:if test="${page != maxpage}">
				<a href="${path}/survey/list?page=${maxpage}">[끝]</a>
			</c:if>
		</td>
	</tr>
</table>
