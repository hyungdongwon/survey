<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table border='1' align='center' height = "500px" width="50%">
		<caption>회원 상세정보</caption>
		<tr>
			<td>아이디</td>
			<td>${dto.member_id }</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${dto.member_name }</td>
		</tr>
		<tr>
			<td>나이</td>
			<td>${dto.member_age }</td>
		</tr>
		<tr>
			<td>핸드폰번호</td>
			<td>${dto.member_phone1}-${dto.member_phone2}-${dto.member_phone3}</td>
		</tr>
		<tr>
			<td rowspan='3'>주소</td>
			<td>
				(우편번호) ${dto.member_addr1 }
			</td>
		</tr>
		<tr>
			<td colspan='2'>
				(도로명주소) ${dto.member_addr2 }
			</td>
		</tr>
		<tr>
			<td>
				(상세주소) ${dto.member_addr3 }
				(참고주소) ${dto.member_addr4 }
			</td>
		</tr>
		<tr>
			<td>가입일자.</td>
			<td>${dto.member_joindate }</td>
		</tr>
	</table>