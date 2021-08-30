/**
 * 
 */
$(function(){
	$('.idCheck').click(function(){
		var inputId = $('input[name="inputId"]').val();
				console.log('inputId: ' + inputId);
				if(inputId != '' && inputId != undefined && inputId != null){
					var request = $.ajax({
						url: "/join/memberIdCheck",
						method: "POST",
						data: { inputId : inputId }, 
						dataType: "json"
					});
					request.done(function( data ) {
						if(data){
							alert("사용가능한 아이디입니다.");
							
							$('#memberJoinForm input').prop('disabled', false);
							$('#memberJoinForm select').prop('disabled', false);
							$('#memberJoinForm button').prop('disabled', false);
						}else{
							alert('중복된 아이디입니다.');
							$('input[name="inputId"]').focus();
						}
						
					}); 
					request.fail(function( jqXHR, textStatus ) {
						alert( "Request failed: " + textStatus );
					});	
				}
	})
})
