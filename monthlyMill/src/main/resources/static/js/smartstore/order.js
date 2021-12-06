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
		var submitFlag = true;
		// 상품이 여러개일 경우
		if(orderedProductNum > 1){
			var receiverName =[]; receiverPhone = []; productName = []; productCode= [] ; detailedProduct= [] ; orderQuantity = [];
			var totOrderAmount= []; option= []; shippingAddr= []; delieveryMsg= [] ; shippingFee= []; orderType= []; expDeliveryDate = [];
			
			//수취인명
			$('.receiverName-input').each(function(){
				if($(this).val() == '' || $(this).val() == undefined){
					alert('수취인명을 입력해주세요.');
					submitFlag = false;
					return submitFlag;
				}else{
					receiverName.push($(this).val());
					submitFlag = true;
					return submitFlag;
				}
			});
			
			//수취인 연락처
			$('.receiverPhone-input').each(function(){
				if($(this).val() == '' || $(this).val() == undefined){
					alert('수취인 연락처를 입력해주세요.');
					submitFlag = false;
					return submitFlag;
				}else{
					receiverPhone.push($(this).val());
					submitFlag = true;
					return submitFlag;
				}
			});
			
			// 상품명 (마케팅용)
			$('.itemName-input').each(function(){
				if($(this).val() == '' || $(this).val() == undefined){
					alert('상품명을 입력해주세요.');
					submitFlag = false;
					return submitFlag;
				}else{
					productName.push($(this).val());
					submitFlag = true;
					return submitFlag;
				}
			});
			
			// 발주품목이름
			$('.orderItemList-input option:checked').each(function(){detailedProduct.push($(this).text());});
			
			// 상품 코드
			$('.orderItemList-input').each(function(){productCode.push($(this).val());});
			
			// 상품 수량
			$('.quantity-input').each(function(){
				if($(this).val() == '' || $(this).val() == undefined){
					alert('상품수량을 입력해주세요.');
					submitFlag = false;
					return submitFlag;
				}else{
					orderQuantity.push($(this).val());
					submitFlag = true;
					return submitFlag;
				}
				});
			
			// 상품별 주문금액
			$('.eachTotalPrice-input').each(function(){
				if($(this).val() == '' || $(this).val() == undefined){
					alert('상품별 주문금액을 입력해주세요.');
					submitFlag = false;
					return submitFlag;
				}else{
					totOrderAmount.push($(this).val());
					submitFlag = true;
					return submitFlag;
				}
			});
			
			// 옵션정보
			$('.optionInfo-input').each(function(){option.push($(this).val());});
			
			// 배송지 주소
			$('.deliveryDestination-input').each(function(){
				if($(this).val() == '' || $(this).val() == undefined){
					alert('배송지 주소를 입력해주세요.');
					submitFlag = false;
					return submitFlag;
				}else{
					shippingAddr.push($(this).val());
					submitFlag = true;
					return submitFlag;
				}
			});
			
			// 배송메세지
			$('.deliveryMsg-input').each(function(){delieveryMsg.push($(this).val());});
			
			// 배송비
			$('.deliveryCharge-input').each(function(){
				if($(this).val() == '' || $(this).val() == undefined){
					alert('배송비를 입력해주세요.');
					submitFlag = false;
					return submitFlag;
				}else{
					shippingFee.push($(this).val());
					submitFlag = true;
					return submitFlag;
				}
			});
			
			// 주문종류
			$('.orderType-input').each(function(){orderType.push($(this).val());});
			
			// 희망일
			$('.orderType-input').each(function(){
				var time = '';
				var date = $(this).parent().parent().find('.deliveryDate-input').val();
				if($(this).val() == 'N스토어'){
					time  = $(this).parent().parent().find('.deliveryDatePart-input').val();
				}else{
					time  = $(this).parent().parent().find('.deliveryDateTime-input').val();
				}
				if(time == '' || time == undefined || date == '' || date == undefined){
					alert('배송 희망날짜와 시간을 모두 입력해주세요.');
					submitFlag = false;
				}else{
					expDeliveryDate.push(date + ' ' + time);
					submitFlag = true;
					return submitFlag;
				}
			});
			console.log(expDeliveryDate);
		// 상품이 하나인 경우
		}else{
			if($('.receiverName-input').val() == '' || $('.receiverName-input').val() == undefined){
				alert('수취인명을 입력해주세요.');
				submitFlag = false;
				return submitFlag;
			}else{
				var receiverName = $('.receiverName-input').val();
			}
			
			if($('.receiverPhone-input').val() == '' || $('.receiverPhone-input').val() == undefined){
				alert('수취인 연락처를 입력해주세요.');
				submitFlag = false;
				return submitFlag;
			}else{
				var receiverPhone = $('.receiverPhone-input').val();
			}
			
			if($('.itemName-input').val() == '' || $('.itemName-input').val() == undefined){
				alert('상품명을 입력해주세요.');
				submitFlag = false;
				return submitFlag;
			}else{
				var productName = $('.itemName-input').text();
			}
			
			if($('.quantity-input').val() == '' || $('.quantity-input').val() == undefined){
				alert('상품수량을 입력해주세요.');
				submitFlag = false;
				return submitFlag;
			}else{
				var orderQuantity = $('.quantity-input').val();
			}
			
			if($('.eachTotalPrice-input').val() == '' || $('.eachTotalPrice-input').val() == undefined){
				alert('상품별 주문금액을 입력해주세요.');
				submitFlag = false;
				return submitFlag;
			}else{
				var totOrderAmount = $('.eachTotalPrice-input').val();
			}
			
			if($('.deliveryDestination-input').val() == '' || $('.deliveryDestination-input').val() == undefined){
				alert('배송지를 입력해주세요.');
				submitFlag = false;
				return submitFlag;
			}else{
				var shippingAddr = $('.deliveryDestination-input').val();
			}
			
			if($('.deliveryCharge-input').val() == '' || $('.deliveryCharge-input').val() == undefined){
				alert('배송비를 입력해주세요.');
				submitFlag = false;
				return submitFlag;
			}else{
				var shippingFee = $('.deliveryCharge-input').val();
			}
			
			var productCode = $('.orderItemList-input').val();
			var detailedProduct = $('.orderItemList-input option:checked').text();
			var option = $('.optionInfo-input').val();
			var delieveryMsg = $('.deliveryMsg-input').val();
			var orderType = $('.orderType-input').val();
			
			
			var expDeliveryDate = $('.deliveryDate-input').val();
			if(expDeliveryDate == '' || expDeliveryDate == undefined){
				alert('희망 배송일을 입력해주세요.');
				submitFlag = false;
				return submitFlag;
			}else{
				if(orderType == 'N스토어'){
					if($('.deliveryDatePart-input').val() == '' || $('.deliveryDatePart-input').val() == undefined){
						alert('희망 배송일 시간을 입력해주세요.');
						submitFlag = false;
						return submitFlag;
					}else{
						expDeliveryDate += ' ' + $('.deliveryDatePart-input').val();
					}
				}else{
					if($('.deliveryDateTime-input').val() == '' || $('.deliveryDateTime-input').val() == undefined){
						alert('희망 배송일 시간을 입력해주세요.');
						submitFlag = false;
						return submitFlag;
					}else{
						expDeliveryDate += ' ' + $('.deliveryDateTime-input').val();
					}
				}
			}
		}
		console.log(submitFlag);
		if(submitFlag){
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
		}
		
	})
	
	// ====== 등록 취소 버튼 ======
	$('.manualCancelBtn').click(function(){
		location.href = "/smartStore/main";
	})
})
