/**
 * 
 */
$(function(){
	var inputId = $('input[name="inputId"]').val();
	var inputPw = $('input[name="inputPw"]').val();
	var inputPwConfirm = $('input[name="inputPwConfirm"]').val();
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
	

	//다음페이지 버튼 클릭 시 입력사항들 확인
	$('.nextPageBtn').click(function(){
		var submitFlag = true;
		if(inputId == '' || inputId == undefined){
			alert('아이디를 입력해주세요');
			$('input[name="inputId"]').focus();
			return;
		}else if($('input[name="idDupCheck"]').val() == 'idDupUncheck'){
			alert('아이디 중복체크를 해주세요');
			$('input[name="inputId"]').focus();
			return;
		}
		
		if(inputPw == '' || inputPw == undefined){
			alert('비밀번호를 입력해주세요');
			$('input[name="inputPw"]').focus();
			return;
		}
		
		$('#memberJoinForm').submit();
	
	});
	
})
