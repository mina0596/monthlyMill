/**
 * 
 */
$(function(){
	// ************** 주문 삭제버튼 이벤트 ***************
	$('.deleteItemBtn').click(function(){
		var deleteCartNum = $(this).parent().parent('tr').find('.result_cart_num').val();
		
		$.ajax({
			url: "/customer/cart/deleteCart",
			method: "post",
			data: { cartNum : deleteCartNum},
			dataType: "text",
			success: function(data){
				alert('장바구니에서 삭제되었습니다.');
			}
		})
		
	})
	
	
	

	// ************** 주문하기 버튼 이벤트 ****************
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