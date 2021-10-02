$(function(){
	
	// ************ 확인 버튼 ***************
	$('.paymentConfirmBtn').click(function(){
		// 유효성 검사
		var submitFlag = true;
		
		if(!$('#purchaseAgree').is(':checked')){
			alert('구매 내역 동의에 체크해주세요.');
			submitFlag = false;	
		}else if($('.depositorName').val() == '' || $('.depositorName').val() == undefined){
			alert('입금자명을 입력해주세요.');
			submitFlag = false;	
		}else if($('.depositorEmail').val() == '' || $('.depositorEmail').val() == undefined){
			alert('입금자 메일을 입력해주세요.');
			submitFlag = false;	
		}
		
		
		// 모두 입력했으면 ajax로 데이터 보내기
		if(submitFlag){
			var params = {
				"depositorName" : $('.depositorName').val(),
				"depositorEmail" : $('.depositorEmail').val()
			}
			
			$.ajax({
				url: "/customer/payment/getDepositInfo",
				method: "POST",
				data: JSON.stringify(params),
				contentType: "application/json",
				dataType: "text",
				success: function(result){
					$('#paymentDepositForm').submit();
				}
			});
			
		}
		
	});
});
