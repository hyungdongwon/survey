<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../_include/inc_header.jsp"%>


	<table border='1' aling="center" align='center' width="50%">
		<caption><h3>회원목록</h3></caption>
		<tr>
			<th>회원번호</th>
			<th>이름</th>
			<th>성별</th>
			<th>나이</th>
			<th>폰번호</th>
		</tr>
		
		<c:forEach var='dto' items="${list }">
			<tr>
				<td>${dto.member_no }</td>
				<td>${dto.member_name}</td>
				<td>${dto.member_gender}</td>
				<td>${dto.member_age}</td>
				<td>${dto.member_phone1}-${dto.member_phone2}-${dto.member_phone3}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan='10' align="center">
				<c:choose>
					<c:when test="${page != 1}">
						<a href="${path}/member/list?page=1">[처음]</a>
					</c:when>
					<c:otherwise>
						[처음]
					</c:otherwise>
				</c:choose>
				
				<c:choose>
					<c:when test="${group != 1}">
						<a href="${path}/member/list?page=${imsigroup-pagesize}">[이전]</a>
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
							<a href="${path}/member/list?page=${i}">[${i}]</a>
						</c:if>
					</c:if>
				</c:forEach>
				
				<c:choose>
					<c:when test="${group != totalgroup && totalgroup != 1}">
						<a href="${path}/member/list?page=${imsigroup+pagesize}">[다음]</a>
					</c:when>
					<c:otherwise>
						[다음]
					</c:otherwise>
				</c:choose>
				
				<c:if test="${page != maxpage}">
					<a href="${path}/member/list?page=${maxpage}">[끝]</a>
				</c:if>
			
			</td>
		</tr>
		
		
	</table>
