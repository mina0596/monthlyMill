// 로그인 & 회원가입


$(function(){
	// =======================================로그인 화면 ============================
	
	
	// ====== 회원가입버튼 ======
	$('.joinBtn').click(function(){
		location.href="/smartStore/join";
	})
	
	// ====== 로그인 버튼 ======
	$('.loginBtn').click(function(){
		
		var params = {
			loginId : $('.loginId').val(),
			loginPw : $('.loginPw').val()
		}
		
		$.ajax({
			url : "/smartStore/getLoginInfo",
			method : "post",
			contentType : "application/json",
			traditional : true,
			data : JSON.stringify(params),
			dataType : "text",
			success : function(result){
				if(result){
					location.href = "/smartStore/main";
				}else{
					alert('비밀번호를 확인후 다시 로그인하여 주시기 바랍니다.');
				}
			}
			
		});
		
	})
	
	
	
	// =======================================회원가입 화면 ============================
	
	// 아이디 중복 체크 + 유효성 검사
	var inputId = $('.inputId').val();
	$('.inputId').keyup(function(){
		$('input[name="idDupCheck"]').val('idDupUncheck');
	});
	
	$('.idCheck').click(function(){
		var regex = /^[a-z][a-z\d]{4,11}$/;
		inputId = $('.inputId').val();
		var result = regex.exec(inputId);
		if(inputId != '' && inputId != undefined && inputId != null && result != null){
			$('.idDupMsg').text('');
			var request = $.ajax({
				url: "/smartStore/join/idDupCheck",
				method: "POST",
				data: { inputId : inputId }, 
				dataType: "json"
			});
			request.done(function( data ) {
				if(data){
					alert("사용가능한 아이디입니다.");
					$('input[name="idDupCheck"]').val('idDupCheck');
				}else{
					alert('이미 사용중인 아이디입니다.');
					$('input[name="inputId"]').focus();
					$('input[name="idDupCheck"]').val('idDupUncheck');
				}
			}); 
			request.fail(function( jqXHR, textStatus ) {
				alert( "관리자에게 연락해주세요." + textStatus );
			});	
		}else if(result == null && result == undefined){
			$('.idDupMsg').text('영어 소문자, 숫자 4-12자리로 입력해주세요.');
			$('input[name="idDupCheck"]').val('idDupUncheck');
		}
		
	})
	
	//비밀번호 유효성검사
	$('input[name="inputPw"]').keyup(function(){
		var regex = /^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/;
    	var result = regex.exec($('input[name="inputPw"]').val());
		if(result == null && result == undefined){
			$('.pwValidationMsg').text('영어대소문자와 숫자, 특수문자를 하나 이상 포함하여 8-18자리로 입력해주세요');
			$('input[name="pWCheck"]').val('unusable');
		}else{
			$('.pwValidationMsg').text('');
			$('input[name="pWCheck"]').val('usable');
		}
	})
	
	//비밀번호 확인
	$('input[name="inputPwConfirm"]').keyup(function(){
		if($('input[name="inputPwConfirm"]').val() != $('input[name="inputPw"]').val()){
			$('.inputPwConfirmMsg').text('비밀번호가 일치하지 않습니다.');
			$('input[name="pWConfirmCheck"]').val('unusable');
		}else if($('input[name="inputPwConfirm"]').val() == $('input[name="inputPw"]').val()){
			$('.inputPwConfirmMsg').text('');
			$('input[name="pWConfirmCheck"]').val('usable');
		}
	});
	
	//이름 유효성검사
	$('input[name="inputName"]').keyup(function(){
		var regex = /[가-힣]{2,}/;
        var result = regex.exec($(this).val());
		if(result == null && result == undefined){
			$('.nameValidationMsg').text('한글만 입력 가능합니다');
			$('input[name="nameCheck"]').val('unusable');
		}else{
			$('.nameValidationMsg').text('');
			$('input[name="nameCheck"]').val('usable');
		}
	})
	
	// 이메일 주소 유효성 검사
	$('input[name="inputEmail"]').blur(function(){
		var regex=/^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
		var inputEmail = regex.test($(this).val());
		if(!inputEmail){
			$('.emailValidationMsg').text('이메일 주소를 정확하게 입력해주세요');
			$('.emailCheck').val('unusable');
		}else{
			$('.emailValidationMsg').text('');
			$('.emailCheck').val('usable');
		}
	})
	
	// ====== 회원가입 완료 버튼 ======
	$('.joinCompleteBtn').click(function(){
		var submitFlag = true;
		
		if($('input[name="inputId"]').val() == '' || $('input[name="inputId"]').val() == undefined){
			alert('아이디를 입력해주세요');
			$('input[name="inputId"]').focus();
			submitFlag = false;
			return submitFlag;
		}else if($('input[name="idDupCheck"]').val() == 'idDupUncheck'){
			alert('아이디 중복체크를 해주세요');
			$('input[name="inputId"]').focus();
			submitFlag = false;
			return submitFlag;
		}
		
		if($('input[name="inputPw"]').val() == '' || $('input[name="inputPw"]').val() == undefined){
			alert('비밀번호를 입력해주세요');
			$('input[name="inputPw"]').focus();
			submitFlag = false;
			return submitFlag;
		}else if($('input[name="pWCheck"]').val() == 'unusable'){
			alert('비밀번호를 영어대소문자, 숫자, 특수문자를 하나 이상 포함하여 8-18자리로 입력해주세요');
			$('input[name="inputPw"]').focus();
			submitFlag = false;
			return submitFlag;
		}
		
		if($('input[name="inputPwConfirm"]').val() == '' || $('input[name="inputPwConfirm"]').val() == undefined){
			alert('비밀번호 확인을 입력해주세요');
			$('input[name="inputPwConfirm"]').focus();
			submitFlag = false;
			return submitFlag;
		}else if($('input[name="pWConfirmCheck"]').val() == 'unusable'){
			alert('비밀번호 확인과 비밀번호가 일치하지 않습니다');
			$('input[name="inputPwConfirm"]').focus();
			submitFlag = false;
			return submitFlag;
		}
		
		if($('input[name="inputName"]').val() == '' || $('input[name="inputName"]').val() == undefined){
			alert('이름을 입력해주세요');
			$('input[name="inputName"]').focus();
			submitFlag = false;
			return submitFlag;
		}else if($('input[name="nameCheck"]').val() == 'unusable'){
			alert('이름은 한글으로만 입력해주세요');
			$('input[name="inputName"]').focus();
			submitFlag = false;
			return submitFlag;
		}
		
		if($('input[name="inputPhone"]').val() == '' || $('input[name="inputPhone"]').val() == undefined){
			alert('휴대폰 번호를 입력해주세요');
			$('input[name="inputPhone"]').focus();
			submitFlag = false;
			return submitFlag;
		}else if($('input[name="phoneCheck"]').val() == 'unusable'){
			alert('휴대폰 번호를 정확하게 입력해주세요');
			$('input[name="inputPhone"]').focus();
			submitFlag = false;
			return submitFlag;
		}
		
		if($('.emailCheck').val() == 'unusable' || $('.emailCheck').val() == undefined){
			alert('이메일 주소를 다시 입력해주세요');
			$('.emailCheck').focus();
			submitFlag = false;
			return submitFlag;
		}
		
		if(submitFlag){
			
			var params = {
				"inputId" : $('.inputId').val(),
				"inputPw" : $('input[name="inputPw"]').val(),
				"inputName" : $('input[name="inputName"]').val(),
				"inputEmail" : $('input[name="inputEmail"]').val(),			
				"inputPhone" : $('input[name="inputPhone"]').val(),			
				"inputDept" : $('input[name="inputDept"]').val()		
			};
			
			var request = $.ajax({
				url: "/smartStore/join/getMemberInfo",
				method: "post",
				traditional: true,
				data: JSON.stringify(params),
				contentType: "application/json",
				dataType: "text"
			});
			
			request.done(function(data){
				alert('회원가입을 완료했습니다. 로그인화면으로 이동합니다.');
				location.href="/smartStore/login";
			});
			request.fail(function(jqXHR, textStatus){
				alert('서버에러: 관리자에게 문의해주세요');
			})
		}
	})
})
