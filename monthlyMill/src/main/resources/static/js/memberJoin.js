/**
 * 
 */
$(function(){
	$('#idCheck').click(function(){
		var inputId = $('input[name="inputId"]').val();
				console.log('inputId: ' + inputId);
				if(inputId != '' && inputId != undefined && inputId != null){
					var request = $.ajax({
						url: "/join/memberIdCheck",
						method: "POST",
						data: { employeeId : employeeId }, 
						dataType: "json"
					});
					request.done(function( data ) {
						if(data){
							alert("사용가능한 아이디입니다.");
							
							$('#addEmployeeForm input').prop('disabled', false);
							$('#addEmployeeForm select').prop('disabled', false);
							$('#addEmployeeForm button').prop('disabled', false);
						}else{
							alert('중복된 아이디입니다.');
							$('input[name="employeeId"]').focus();
						}
						
					}); 
					request.fail(function( jqXHR, textStatus ) {
						alert( "Request failed: " + textStatus );
					});	
				}
	})
})


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

$(document).on("keyup", "#phoneNum", function(){
	this.value = autoHypenPhone(this.value);
});