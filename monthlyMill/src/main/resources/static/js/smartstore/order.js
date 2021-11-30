/*
	주문에 대한 js
*/


$(function(){
	
	
	// *******************************************************************
	// ********************** 수기 주문 등록 화면 *****************************
	// *******************************************************************
	
	// ====== 등록 버튼 ======
	$('.manualEnterBtn').click(function(){
		var parmas = {
			
		}
		
		$.ajax({
			url : "smartStore/addOrder/manually",
			method : "post",
			contentType : "application/json",
			data : JSON.stringify(params),
			traditional : true,
			dataType : "text",
			success : function(result){
				if(result){
					alert("새로운 주문이 등록되었습니다.");
					location.href="/smartStore/main";
				}else{
					alert("등록한 내용을 다시 확인해주세요.");
				}
			}
		})
	})
	
	// ====== 등록 취소 버튼 ======
	$('.manualCancelBtn').click(function(){
		location.href = "/smartStore/main";
	})
})
