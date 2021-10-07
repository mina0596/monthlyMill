/**
 * 
 */
$(function(){
	// ************** 주문 삭제버튼 이벤트 ***************
	$('.orderCancelBtn').click(function(){
		var deleteOrderNum = $(this).parent().parent().find('.orderNumber').text();
		console.log(deleteOrderNum);
		$('#getCancelOrderNum').submit();
	});

	
});