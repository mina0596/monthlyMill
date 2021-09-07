/**
 * 
 */
 $(function(){
	//(만)나이 계산하는 함수
	function calcAge(birth){
		var date = new Date();
		var year = date.getFullYear();
		var month = (date.getMonth() + 1);
		var day = date.getDate();
		
		if(month < 10) month = '0' + month;
		if(day < 10) day = '0' + day;
		
		var monthDay = month + day;
		birth = birth.replace('-', '').replace('-', '');
		
		var birthdayy = birth.substr(0, 4);
		var birthdaymd = birth.substr(4, 4);
		var age = monthDay < birthdaymd ? year - birthdayy - 1 : year - birthdayy;
		
		return age;
		
	}
	
	// **************** 기본사항 입력***********************
	var inputId = $('input[name="inputId"]').val();
	var inputName = $('input[name="inputName"]').val();
	var inputAge = $('input[name="inputAge"]').val();
	var inputSex = $('input:radio[name="inputSex"]').is(':checked');
	
	//아이디 중복 체크 + 아이디 유효성검사
	$('input[name="inputId"]').keyup(function(){
		$('input[name="idDupCheck"]').val('idDupUncheck');
	});
	$('.idCheck').click(function(){
		var regex = /^[a-z][a-z\d]{4,11}$/;
		inputId = $('input[name="inputId"]').val();
		var result = regex.exec(inputId);
		if(inputId != '' && inputId != undefined && inputId != null && result != null){
			$('.idDupMsg').text('');
			var request = $.ajax({
				url: "/join/memberIdCheck",
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
	});
	
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
	
	//휴대폰번호 유효성검사
	$('input[name="inputPhone"]').mouseout(function(){
		var patternPhone = /01[016789]-[^0][0-9]{2,3}-[0-9]{3,4}/;
		var result = patternPhone.exec($(this).val());
		if(result == null && result == undefined){
			$('.phoneValidationMsg').text('휴대폰 번호를 정확하게 입력해주세요');
			$('input[name="phoneCheck"]').val('unusable');
		}else{
			$('.phoneValidationMsg').text('');
			$('input[name="phoneCheck"]').val('usable');
		}

	})
	
	//다음페이지 버튼 클릭 시 입력사항들 확인
	$('.joinBasicNextBtn').click(function(){
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
			alert('닉네임은 한글으로만 입력해주세요');
			$('input[name="inputName"]').focus();
			submitFlag = false;
			return submitFlag;
		}
		
		if($('input[name="inputBYear"]').val() == '' 
		|| $('input[name="inputBYear"]').val() == undefined
		|| $('select[name="inputBMonth"]').val() == ''
		|| $('select[name="inputBMonth"]').val() == undefined
		|| $('select[name="inputBDay"]').val() == ''
		|| $('select[name="inputBDay"]').val() == undefined){
			alert('생년월일을 입력해주세요');
			$('input[name="inputBYear"]').focus();
			submitFlag = false;
			return submitFlag;
		}
		
		if(!$('input:radio[name="inputSex"]').is(':checked')){
			alert('성별을 입력해주세요');
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
		
		$('.inputBday').val($('input[name="inputBYear"]').val() + '-' + $('select[name="inputBMonth"]').val() 
		+ '-' + $('select[name="inputBDay"]').val());
		
		
		$('.inputAge').val(calcAge($('.inputBday').val()));
		
		if(submitFlag) $('#joinBasic').submit();
	
	});
	
	// **************** 약관동의 페이지 ***********************
	$('.joinAgreementNextBtn').click(function(){
		var submitFlag = true;
		if(!$('#termAgree').is(':checked') || !$('#infoAgree').is(':checked') || !$('#locationAgree').is(':checked')){
			alert('필수항목에 모두 동의해주세요');
			submitFlag = false;
			return submitFlag;
		}
		if(submitFlag) $('#joinTerms').submit();
	})
	
	// **************** 추가입력 페이지 ***********************
	$('.getCouponBtn').click(function(){
		alert('할인쿠폰이 제공되었습니다🤘🎫🤘🎫');
		$('#joinAddInfo').submit();
	})
	
})


/* 프론트 파트 */

// 본인인증 번호입력 시 하이픈 자동추가
let autoHypenPhone = function(str){
	str = str.replace(/[^0-9]/g, '');
	let tmp = '';
	if( str.length < 4){
		return str;
	}else if(str.length < 7){
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3);
		return tmp;
	}else if(str.length < 11){
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 3);
		tmp += '-';
		tmp += str.substr(6);
		return tmp;
	}else{              
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 4);
		tmp += '-';
		tmp += str.substr(7);
		return tmp;
	}
    return str;
        
}

$(document).on("keyup", ".phoneNum", function(){
	this.value = autoHypenPhone(this.value);
});
