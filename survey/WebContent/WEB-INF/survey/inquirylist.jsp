<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../_include/inc_header.jsp" %>
<table border="1" align='center' style="width:90%;">
	<caption>1:1문의 목록</caption>
	<tr>
		<th>번호</th>
		<th>문의유형</th>
		<th>제목</th>
		<th>작성자</th>
		<th>등록일</th>
		<th>공개 여부</th>
		<th>처리 상태</th>
	</tr>
	<c:forEach var="dto" items="${list}">
	<tr>
		<form id="form1" name='form1' method='post' action="${path}/survey/inquiryselect">
			<td>${dto.inquiry_no}</td>
			<td>${dto.inquiry_type }</td>
			<td><a href='${path}/survey/inquiryselect?inquiry_no=${dto.inquiry_no}&member_name=${dto.member_name}'>${dto.inquiry_subject }</td>
			<td>${dto.member_name }</td>
			<td>${dto.inquiry_date }</td>
			<td>${dto.inquiry_disclosure }</td>
			<td>${dto.inquiry_complete}</td>
			<input type="hidden" name="member_name" value="${dto.member_name}"/>
			<input type="hidden" name="inquiry_no" value="${dto.inquiry_no}"/>
			<input type="hidden" name="inquiry_disclosure" value="${dto.inquiry_disclosure }"/>
			<input type='hidden' name="inquiry_complete" value="${dto.inquiry_complete}">
		</form>
 	</tr>
	</c:forEach>
	<tr>
		<td colspan='7' align='center'>
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
	<tr border='0'>
		<td align='right' colspan='10'>
			<a href="${path}/survey/inquiry">1:1문의 작성</a>
		</td>
	</tr>
</table>
<script>
function aaa(){
	document.getElementById('form1').submit();
}

</script>
