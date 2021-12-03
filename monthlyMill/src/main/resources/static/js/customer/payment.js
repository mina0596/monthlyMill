/**
 * 
 */

function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
            
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postCode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("addressDetail").focus();
        }
    }).open();
}


$(function(){
	$('.getPostNumber').click(function(){
		sample6_execDaumPostcode();
	})
	
	// ******************주문자와 동일버튼 누르면 정보가져오기 *************
	$('#sameWithOrder').change(function(){
		if($(this).is(':checked')){
			$('.inputReceiverName').val($('.inputOrdererName').val());
			$('.inputReceiverPhoneNum').val($('.inputOrdererPhoneNum').val());
			
			$.ajax({
				url: "/customer/payment/getMemberInfo",
				method: "POST",
				contentType: "application/json", 
				dataType: "json",
				success: function(result){
					$('.postCode').val(result.mPostalCode);
					$('.inputReceiverAdress').val(result.mAddr);
					$('.inputReceiverAddressDetail').val(result.mDetailAddr);
				}
			});
		}else{
			$('.inputReceiverName').val('');
			$('.inputReceiverPhoneNum').val('');
			$('.postCode').val('');
			$('.inputReceiverAdress').val('');
			$('.inputReceiverAddressDetail').val('');
		}
	});
});

	// *********************** 결제하기 버튼 이벤트 *****************
	$('.paymentBtn').click(function(){
		var submitCheck = true;
		var expDeliveryDate = '';
		// 이용약관 동의 받기
		/*
		var checkNum = 0;
		var agreementNum = $('input[class="checkRequired checkbox"]').length;
		$('.agreement_column').each(function(){
			if($(this).find('.checkbox').is(':checked')){
				checkNum += 1;
			}
		})
		*/
		// **************** 유효성 검사 **************
		if($('.inputOrdererName').val() == '' || $('.inputOrdererName').val() == undefined){
			alert('주문자 이름을 입력해주세요.');
			$('.inputOrdererName').focus();
			submitCheck = false;
		}else if($('.inputOrdererPhoneNum').val() == '' || $('.inputOrdererPhoneNum').val() == undefined){
			alert('주문자 휴대폰 번호를 입력해주세요.');
			$('.inputOrdererPhoneNum').focus();
			submitCheck = false;
		}else if($('.inputReceiverName').val() == '' || $('.inputReceiverName').val() == undefined){
			alert('받는분 이름을 입력해주세요.');
			$('.inputReceiverName').focus();
			submitCheck = false;
		}else if($('.inputReceiverPhoneNum').val() == '' || $('.inputReceiverPhoneNum').val() == undefined){
			alert('받는분 휴대폰 번호를 입력해주세요.');
			$('.inputReceiverPhoneNum').focus();
			submitCheck = false;
		}else if($('input[name="inputPostCode"]').val() == '' || $('input[name="inputPostCode"]').val() == undefined){
			alert('받는분 우편번호를 입력해주세요.');
			$('input[name="inputPostCode"]').focus();
			submitCheck = false;
		}else if($('.inputReceiverAdress').val() == '' || $('.inputReceiverAdress').val() == undefined){
			alert('받는분 주소를 입력해주세요.');
			$('.inputReceiverAdress').focus();
			submitCheck = false;
		}else if($('.reservationDate').val() == '' || $('.reservationDate').val() == undefined){
			alert('예약날짜를 선택해주세요.');
			submitCheck = false;			
		}else if($('.reservationDeliveryType').val() == '퀵'){
			if($('.reservationTime').val() == '' || $('.reservationTime').val() == undefined){
				alert('퀵 시간을 선택해주세요.');
				submitCheck = false;
			}else{
				expDeliveryDate =  $('.reservationDate').val() + $('.reservationTime').val();
			}
		}else if($('.reservationDeliveryType').val() == '택배'){
			expDeliveryDate =  $('.reservationDate').val();
		}else{
			submitCheck = true;
		}
		
		
		var cartNum = [];
		$('.result_cart_num').each(function(){
				cartNum.push($(this).val());
			})
		
		var params = {
			"receiverName" : $('.inputReceiverName').val(),
			"receiverPhone" : $('.inputReceiverPhoneNum').val(),
			"receiverPostalCode" : $('input[name="inputPostCode"]').val(),
			"receiverAddr" : $('.inputReceiverAdress').val(),
			"receiverDetailAddr" : $('.inputReceiverAddressDetail').val(),
			"totalItemPrice" : $('.itemTotalPrice').text(),
			"discountAmount" : $('.discountTotalPrice').text(),
			"shippingFee" : $('.deliveryTotalPrice').text(),
			"totalPrice" : $('.paymentTotalPrice').val(),
			"receiptType" : $('select[class="receiptApplyType"]').val(),
			"receiptNumType" : $('select[class="receiptNumberType"]').val(),
			"receiptNum" : $('.inputReceiptApplicantPhoneNum').val(),
			"cartNum": cartNum,
			"expDeliveryDate" : expDeliveryDate,
			"shippingMethod" : $('.reservationDeliveryType').val(),
		}
		
		if(submitCheck){
			$.ajax({
				url: "/customer/payment/getOrderInfo",
				method: "POST",
				data: JSON.stringify(params),
				contentType: 'application/json',
				traditional: true,
				dataType: 'text',
				success: function(result){
					$('#paymentInfo').submit();
				}
			});
			
			
		}
	})
	
	
