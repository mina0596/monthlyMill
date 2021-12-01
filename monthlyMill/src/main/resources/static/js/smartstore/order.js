/*
	주문에 대한 js
*/


$(function(){
	
	
	// *******************************************************************
	// ********************** 수기 주문 등록 화면 *****************************
	// *******************************************************************
	
	// ====== 등록 버튼 ======
	$('.manualEnterBtn').click(function(){
		
		// 상품이 몇개인지 확인
		var orderedProductNum = $('.manualListItem').length;
		
		// 상품이 여러개일 경우
		if(orderedProductNum > 1){
			var receiverName =[]; receiverPhone = []; productName = []; productCode= [] ; detailedProduct= [] ; orderQuantity = [];
			var totOrderAmount= []; option= []; shippingAddr= []; delieveryMsg= [] ; shippingFee= []; orderType= []; expDeliveryDate = [];
			
			//수취인명
			$('.receiverName-input').each(function(){receiverName.push($(this).val());});
			
			//수취인 연락처
			$('.receiverPhone-input').each(function(){receiverPhone.push($(this).val());});
			
			// 상품명 (마케팅용)
			$('.itemName-input').each(function(){productName.push($(this).val());});
			
			// 발주품목이름
			$('.orderItemList-input option:checked').each(function(){detailedProduct.push($(this).text());});
			
			// 상품 코드
			$('.orderItemList-input').each(function(){productCode.push($(this).val());});
			
			// 상품 수량
			$('.quantity-input').each(function(){orderQuantity.push($(this).val());});
			
			// 상품별 주문금액
			$('.eachTotalPrice-input').each(function(){totOrderAmount.push($(this).val());});
			
			// 옵션정보
			$('.optionInfo-input').each(function(){option.push($(this).val());});
			
			// 배송지 주소
			$('.deliveryDestination-input').each(function(){shippingAddr.push($(this).val());});
			
			// 배송메세지
			$('.deliveryMsg-input').each(function(){delieveryMsg.push($(this).val());});
			
			// 배송비
			$('.deliveryCharge-input').each(function(){shippingFee.push($(this).val());});
			
			// 주문종류
			$('.orderType-input').each(function(){orderType.push($(this).val());});
			
			// 희망일
			$('.orderType-input').each(function(){
				var time = '';
				var date = $(this).parent().parent().find('.deliveryDate-input').val();
				if($(this).val() == 'N스토어'){
					time  = $(this).parent().parent().find('.deliveryDatePart-input').val();
					expDeliveryDate.push(date + ' ' + time);
				}else{
					time  = $(this).parent().parent().find('.deliveryDateTime-input').val();
					expDeliveryDate.push(date + ' ' + time);
				}
			});
			console.log(expDeliveryDate);
			
			
		// 상품이 하나인 경우
		}else{
			var receiverName = $('.receiverName-input').val();
			var receiverPhone = $('.receiverPhone-input').val();
			var productName = $('.itemName-input').text();
			var productCode = $('.orderItemList-input').val();
			var detailedProduct = $('.orderItemList-input option:checked').text();
			var orderQuantity = $('.quantity-input').val();
			var totOrderAmount = $('.eachTotalPrice-input').val();
			var option = $('.optionInfo-input').val();
			var shippingAddr = $('.deliveryDestination-input').val();
			var delieveryMsg = $('.deliveryMsg-input').val();
			var shippingFee = $('.deliveryCharge-input').val();
			var orderType = $('.orderType-input').val();
			var expDeliveryDate = $('.deliveryDate-input').val();
			if(orderType == 'N스토어'){
				expDeliveryDate += ' ' + $('.deliveryDatePart-input').val();
			}else{
				expDeliveryDate += ' ' + $('.deliveryDateTime-input').val();
			}
		}
		
		var params = {
			orderedProductNum : orderedProductNum,
			orderNum : $('.orderCode-input').val(),
			shippingDate : $('.sendDate-input').val(),
			paidDate : $('.paymentDate-input').val() + ' ' + $('.paymentTime-input').val(),
			shippingMethod : $('.deliveryType-input').val(),
			ordererName : $('.purchaserName-input').val(),
			orderderPhone : $('.purchaserPhone-input').val(),
			// 상품수에 따라 달라짐
			receiverName : receiverName,
			receiverPhone : receiverPhone,
			productName : productName,
			productCode : productCode,
			detailedProduct : detailedProduct,
			orderQuantity : orderQuantity,
			totOrderAmount : totOrderAmount,
			option : option,
			shippingAddr : shippingAddr,
			delieveryMsg : delieveryMsg,
			shippingFee : shippingFee,
			orderType : orderType,
			expDeliveryDate : expDeliveryDate
		}
		
		$.ajax({
			url : "/smartStore/addOrder/manually",
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
