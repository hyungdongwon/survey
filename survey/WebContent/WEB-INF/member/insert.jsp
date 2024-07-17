<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../_include/inc_header.jsp" %>
 <script src="https://code.jquery.com/jquery-latest.min.js"></script>

<form name='insertform'>
	<table border='1' align='center' width='80%' height="800px" >
		<caption>회원가입</caption>
		<c:if test="${code != null}">
		<tr>
			<th>사업자등록번호</th>
			<td><input type='text' name='business_number' placeholder="-를제외하고 입력해주세요"></td>
			<input type='hidden' name='member_level' value="1"/>
		</tr>
		</c:if>
		<tr>
			<th>아이디</th>
			<td>
				<input type='text' id="id" name='member_id'/>
				<button type='button' onclick='idcheck();'>아이디 체크</button> 
				<input type='hidden' name='result' id='result'/>
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type='password' name='member_password'></td>
		</tr>
		<tr>
			<th>비밀번호 확인</th>
			<td><input type='password' name='member_passwordcheck'></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type='text' name='member_name'></td>
		</tr>
		<tr>
			<th>주민번호</th>
			<td><input type='text' name='member_birth' size=3 maxlength=6>-<input type='text' name='member_ssn' size=1 maxlength=1>******</td>
		</tr>
		<tr>
			<th>핸드폰번호</th>
			<td>
				<select name='member_phone1'>
					<option value='010'>010</option>
					<option value='011'>011</option>
					<option value='016'>016</option>
				</select>-
				<input type='text' name='member_phone2' size=3 mexlength=4>-
				<input type='text' name='member_phone3' size=3 mexlength=4>
			</td>
		</tr>
		<tr>
			<th rowspan='3'>주소</th>
			<td>
				<input type="text" id="sample6_postcode" placeholder="우편번호" name='member_addr1'>
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
			</td>
		</tr>
		<tr>
			<td colspan='2'>
				<input type="text" id="sample6_address" placeholder="주소" name='member_addr2'>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" id="sample6_detailAddress" placeholder="상세주소" name='member_addr3'>
				<input type="text" id="sample6_extraAddress" placeholder="참고항목" name='member_addr4'>
			</td>
		</tr>
		<tr>
			<td colspan='2' align='center'><button type='button' onclick='join();'>join</button></td>
		</tr>
	</table>
</form>

<script>
function join(){
	var f = document.insertform;
	
	if(f.member_id.value == "" || f.member_id.value == null){
		alert("ID를 입력해주세요");
		f.member_id.focus();
		return false;
	}
	if(f.member_password.value == "" || f.member_password.value == null){
		alert("password를 입력해주세요");
		f.member_password.focus();
		return false;
	}
	if(f.member_passwordcheck.value == "" || f.member_passwordcheck.value == null){
		alert("비밀번호 확인을 입력해주세요");
		f.member_passwordcheck.focus();
		return false;
	}
	
	if(f.member_password.value != f.member_passwordcheck.value){
		alert("비밀번호 가 일치하지 않습니다.");
		f.member_password.focus();
		return false;
	}
	
	if(f.member_name.value == "" || f.member_name.value == null){
		alert("이름을 입력해주세요.");
		f.member_name.focus();
		return false;
	}
	
	if(f.member_birth.value == "" || f.member_birth.value == null){
		alert("생년월일 을 입력해주세요");
		f.member_birth.focus();
		return false;
	}
	if(f.member_ssn.value == "" || f.member_ssn.value == null){
		alert("주민번호 앞자리를 입력해주세요");
		f.member_ssn.focus();
		return false;
	}
	if(f.member_phone1.value == "" || f.member_phone1.value == null){
		alert("핸드폰번호를 입력해주세요");
		f.member_phone1.focus();
		return false;
	}
	if(f.member_phone2.value == "" || f.member_phone2.value == null){
		alert("핸드폰번호를 입력해주세요");
		f.member_phone2.focus();
		return false;
	}
	if(f.member_phone3.value == "" || f.member_phone3.value == null){
		alert("핸드폰번호를 입력해주세요");
		f.member_phone3.focus();
		return false;
	}
	
	if(f.member_addr1.value == "" || f.member_addr1.value == null){
		alert("우편번호를 입력해주세요");
		f.member_addr1.focus();
		return false;
	}
	if(f.member_addr2.value == "" || f.member_addr2.value == null){
		alert("주소를 입력해주세요");
		f.member_addr2.focus();
		return false;
	}
	if(f.member_addr3.value == "" || f.member_addr3.value == null){
		alert("상세주소를 입력해주세요");
		f.member_addr3.focus();
		return false;
	}
	if(f.member_addr4.value == "" || f.member_addr4.value == null){
		alert("참고항목을 입력해주세요");
		f.member_addr4.focus();
		return false;
	}
	
	var id = $("#id").val();
	var result = $("#result").val();
	
	if(id == result && id.length > 0 && result.length > 0){
		
	}else{
		alert("아이디를 다시 체크 해주세요");
		$('#id').val('');
		$('#result').val('');
		$('#id').focus();
		return false;
	}
	
	if(confirm("가입하시겠습니까?")){
		insertform.action='${path}/member/insertProc';
		insertform.method='post';
		insertform.submit();
	}
	
}
</script>

<script>
function idcheck(){
	var id = $("#id").val();
	
	if(id==""){
		alert("아이디를 입력하세요");
		$("#id").focus();
		return;
	}
	$.ajax({
		type: "post",
		url: "${path}/memberidcheck/idcheckProc",
		data: $('form').serialize(),
		success: function(data){
			
			$("#id").val(id);
			$("#result").val(data);
			if($("#result").val() == "null"){
				$("#result").val(id);
				alert('사용가능한 ID입니다');
			}else{
				alert('사용불가능 ID입니다');
			}
		}
	});
}
</script>


<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>

