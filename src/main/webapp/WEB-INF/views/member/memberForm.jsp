<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<link rel="stylesheet" type="text/css" href="/code02/resources/css/style.css">
<style type="text/css">
	input::placeholder {
		font-size: 14px;
	}
</style>

</head>
<body>
	<jsp:include page="/resources/include/top.jsp"/>
	<div style="width: 500px; margin-left: auto; margin-right: auto;">
		<div><h3>회원가입</h3></div>
		<form name="frmMember" action="/code02/member/addMember" method="post">
			<table class="table table-borderless">
				<tr>
					<td>
						<span>아이디</span>
						<input class="form-control" type="text" name="id" id="id">
						<div class="form-text" id="messageId"></div>
					</td>
				</tr>
				<tr>
					<td>
						비밀번호
						<input class="form-control" type="password" name="password" id="password">
					</td>
				</tr>
				<tr>
					<td>
						비밀번호 재확인
						<input class="form-control" type="password" id="passwordCheck">
						<div class="form-text" id="messageCheckPassword"></div>
					</td>
				</tr>
				<tr>
					<td>
						이름
						<input class="form-control" type="text" name="name">
					</td>
				</tr>
				<tr>
					<td>
						이메일<span style="font-size: 12px; color: gray;"> (선택)</span>
						<input class="form-control" type="text" name="email" id="email">
						<div class="form-text" id="messageEmail"></div>
					</td>
				</tr>
				<tr>
					<td>
						생년월일<span style="font-size: 12px; color: gray;"> (선택)</span>
						<input class="form-control" type="text" name="birthDay" id="birthDay" placeholder="생년월일 8자리를 입력해주세요 ex)19990101">
						<div class="form-text" id="messageBirthDay"></div>
					</td>
				</tr>
				<tr>
					<td>
						전화번호<span style="font-size: 12px; color: gray;"> (선택)</span>
						<input class="form-control" type="text" name="phoneNumber" id="phone">
						<div class="form-text" id="messagePhone"></div>
					</td>
				</tr>
				<tr>
					<td>
						<input class="btn btn-secondary" type="button" value="가입하기" onclick="fn_sendMember()">
					</td>
				</tr>
			</table>
		</form>
	</div>
	
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	
	var okId = 'no';
	var okPassword = 'no';
	var okName = 'no';
	var okEmail = 'yes';
	var okBirthDay = 'yes';
	var okPhone = 'yes';
	
	
	$("#id").change(function() {
		
		okId = 'no';
		
		if($('#id').val() == "" || $('#id').val().length == 0) {
			$('#messageId').text("아이디를 입력해주세요.");
			
		} else {
			checkId();	
		}
	});
	
	$("#email").change(function() {
		okEmail = 'no';
		var _email = $('#email').val();
		
		
		if( _email == "" || _email.length == 0) {
			okEmail = 'yes';
			$('#messageEmail').text("");
			
		} else {
			var reg_email = /^([0-9a-zA-Z]+)@([0-9a-zA-Z])/;
			
			if(!reg_email.test(_email)) {   
				$('#messageEmail').text("이메일 형식을 지켜주세요. ex) ~~~@mycode.com");
			} else {
				checkEmail();	
			}
		}
	});
	
	$("#phone").change(function() {
		okPhone = 'no';
		
		if($('#phone').val().length != 13) {
			$('#messagePhone').text("휴대폰 번호 11자를 맞춰써주세요.");
			
		} else if($('#phone').val() == "" || $('#phone').val().length == 0) {
			okPhone = 'yes';
			$('#messagePhone').text("");
			
		} else {
			checkPhone();
		}
	});
	
	$('#password').change(function() {
		okPassword = 'no';
		$('#passwordCheck').val("");
		$('#messageCheckPassword').text("비밀번호를 확인해주세요");
	});
	
	$("#passwordCheck").change(function() {
		okPassword = 'no';
		
		if($('#password').val() == $('#passwordCheck').val() ) {
			$('#messageCheckPassword').text("확인 되었습니다.");
			okPassword = 'yes';
		} else {
			$('#messageCheckPassword').text("비밀번호가 다릅니다.");
			okPassword = 'no';
		}
	});
	
	$(document).on("keyup", "#id", function() { 
		$(this).val( $(this).val().replace(/[^a-zA-Z0-9]/g, "")); 
	});
	
	$(document).on("keyup", "#phone", function() { 
		$(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--", "-") ); 
	});
	
	$(document).on("keyup", "#birthDay", function() { 
		$(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/([0-9]{4})([0-9]{2})([0-9]{2})$/,"$1-$2-$3")); 
	});
	
	$("#birthDay").change(function() {
		okBirthDay = 'no';
		
		var isBirth = isBirthday($('#birthDay').val());
		
		if(isBirth) {
			$('#messageBirthDay').text("확인 되었습니다.");
			okBirthDay = 'yes';
		} else {
			$('#messageBirthDay').text("생년월일 8자리를 정확히 입력해주세요.");
			okBirthDay = 'no';
		}
		
		if($('#birthDay').val() == "" || $('#birthDay').val().length == 0) {
			okBirthDay = 'yes';
			$('#messageBirthDay').text("");
		}
	});
	
	function checkId() {
		var _id = $("#id").val();
		
		$.ajax({
			type:"get",
			async:true,
			url:"/code02/member/duplicateId",
			dataType:"text",
			data:{id: _id},
			success:function (data, textStatus) {
				if(data == 'usable') {
					$('#messageId').text("사용할 수 있는 아이디입니다.");
					okId = 'yes';
				} else {
					$('#messageId').text("이미 사용 중인 아이디입니다.");
					okId = 'no';
				}
			},
			error:function (data,textStatus) {
				alert("에러가 발생했습니다.");
			},
			complete:function (data,textStatus) {
				
			}
		});
	} 
	
	function checkEmail() {
		var _email = $("#email").val();
		
		$.ajax({
			type:"get",
			async:true,
			url:"/code02/member/duplicateEmail",
			dataType:"text",
			data:{email: _email},
			success:function (data, textStatus) {
				if(data == 'usable') {
					$('#messageEmail').text("사용할 수 있는 이메일입니다.");
					okEmail = 'yes';
				
				} else {
					$('#messageEmail').text("이미 사용 중인 이메일입니다.");
					okEmail = 'no';
				}
			},
			error:function (data,textStatus) {
				alert("에러가 발생했습니다.");
			},
			complete:function (data,textStatus) {
				
			}
		});
	} 
	
	function checkPhone() {
		var _phone = $("#phone").val();
		
		$.ajax({
			type:"get",
			async:true,
			url:"/code02/member/duplicatePhone",
			dataType:"text",
			data:{phone: _phone},
			success:function (data, textStatus) {
				if(data == 'usable') {
					$('#messagePhone').text("사용할 수 있는 휴대전화 번호입니다.");
					okPhone = 'yes';
				
				} else {
					$('#messagePhone').text("이미 사용 중인 휴대전화 번호입니다.");
					okPhone = 'no';
				}
			},
			error:function (data,textStatus) {
				alert("에러가 발생했습니다.");
			},
			complete:function (data,textStatus) {
				
			}
		});
	} 
	
	function isBirthday(dateStr) { 
		var year = Number(dateStr.substr(0,4)); // 입력한 값의 0~4자리까지 (연) 
		var month = Number(dateStr.substr(5,2)); // 입력한 값의 4번째 자리부터 2자리 숫자 (월) 
		var day = Number(dateStr.substr(8,2)); // 입력한 값 6번째 자리부터 2자리 숫자 (일) 
		var today = new Date(); // 날짜 변수 선언 
		var yearNow = today.getFullYear(); // 올해 연도 가져옴 
		
		if (dateStr.length <=10) {
			
			if (1900 > year || year > yearNow){ 
				return false;
			} else if (month < 1 || month > 12) { 
				return false; 
			} else if (day < 1 || day > 31) { 
				return false; 
			} else if ((month==4 || month==6 || month==9 || month==11) && day==31) { 
				return false; 
			} else if (month == 2) { 
				var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)); 
				if (day>29 || (day==29 && !isleap)) { 
					return false; 
				} else { 
					return true; 
				}
			} else { 
				return true; 
			} 
		} else { 
			return false; 
		}
	}
	
	function CheckEmail(str) {                                                  
		                          
	}             

	function fn_sendMember() {
		var frmMember = document.frmMember;
		var id = frmMember.id.value;
		var password = frmMember.password.value;
		var name = frmMember.name.value;
		var email = frmMember.email.value;
		
		if(okId == 'no') {
			alert("아이디를 입력해주세요.");
			
		} else if(okPassword == 'no') {
			alert("비밀번호를 확인해주세요.");
			
		} else if(name.length == 0 || name == "") {
			alert("이름을 입력해주세요.");
			
		} else if(okEmail == 'no') {
			alert("이메일을 제대로 입력해주세요");
			
		} else if(okBirthDay == 'no') {
			alert("생년월일을 제대로 입력해주세요");
			
		} else if(okPhone == 'no') {
			alert("핸드폰번호를 제대로 입력해주세요");
			
		} else {
			frmMember.method = "post";
			frmMember.action = "/code02/member/addMember";
			frmMember.submit();
		} 
	}
</script>
</body>
</html>