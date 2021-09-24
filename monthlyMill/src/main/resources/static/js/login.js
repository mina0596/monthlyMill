/**
 * 
 */
$(function(){
	$('.loginBtn').click(function(){
		var inputMType = $('input[name=loginType]:checked').val();
		var inputId = $('.inputId').val();
		var inputPw = $('.inputPw').val();
		
		var param = {
			"inputMType" : inputMType,
			"inputId" : inputId,
			"inputPw" : inputPw
		};
		
		
		var request = $.ajax({
			url: "/login/getInfo",
			data: JSON.stringify(param),
			dataType: "text",
			method: "post",
			traditional: true,
			contentType: "application/json",
		});
		
		request.done(function(data){
			
		});
		request.fail(function(jqXHR, textStatus){
			alert('서버에러: 관리자에게 문의해주세요');
		})
	});
});