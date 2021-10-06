/**
 * 
 */
$(function(){
	$('.gotoPaymentBtn').click(function(){
		var itemQuantities = $('.result_itemList').children('tr').find('.itemQuantity');
		var cartNum = [];
		var pAmount = []; 
		
		itemQuantities.each(function(){
			$(this).parent('td').children('.pAmount').val($(this).text());
			pAmount.push($(this).parent('td').children('.pAmount').val());
		});
		$('.result_cart_num').each(function(){
			cartNum.push($(this).val());
		})
		
		var params = {
			"cartNum" : cartNum,
			"pAmount" : pAmount
		};
		
		var request = $.ajax({
			url: "/customer/cart/itemCartInfo",
			method: "post",
			traditional: true,
			data: JSON.stringify(params),
			contentType: "application/json", 
			dataType: "text",
		});
		
		request.done(function(data){
			location.href="/customer/payment/paymentInfo";
		});
		
		request.fail(function(){
			alert('서버오류입니다.');
		})
	});

	
});