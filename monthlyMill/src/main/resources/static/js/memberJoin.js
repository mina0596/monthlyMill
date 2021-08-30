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
