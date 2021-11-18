/**
 * 
 */
$(function(){
	// 할인 미설정시 disabled:disabled 로 설정됨.
	$('#discount_on').click(function(){
		$('.discount_number').attr('disabled', false);
		$('.discount_number').val('0');
	})
	$('#discount_off').click(function(){
		$('.discount_number').attr('disabled', true);
	})
	
	// 재고량 미설정시 disabled:disabled 로 설정됨.
	$('#quantity_on').click(function(){
		$('.limited_quantity_number').attr('disabled', false);
		$('.limited_quantity_number').val('0');
	})
	$('#quantity_off').click(function(){
		$('.limited_quantity_number').attr('disabled', true);
	})
	
	// 할인가 설정시 할인 적용가 자동계산 - 작업예정
	
	$('.previewBtn').click(function(){
		
		var params = {
			pCode: $('.product_code').val(),
			pName : $('.product_name').val(),
			pPrice: $('.product_price').val(),
			discountAmount: $('.discount_number').val(),
			discountType: $('select[id="discount_type"]').val(),
			sellStartDate: $('.sale_start_date').val(),
			sellCompDate: $('.sale_complete_date').val(),
			vatType: $('input[name="product_tax"]').val(),
			quantityLimit: $('.limited_quantity_number').val(),
			detail: $('#product_detail').val(),
			nutrition: $('.nutrition_info').val(),
			recipe: $('.recipe_info').val(),
			defaultShippingFee: $('.default_shipping_fee').val()
		}
		
		$.ajax({
			url: "/admin/productRegister/getProductInfo",
			method: "post",
			data: JSON.stringify(params),
			contentType: "application/json",
			tradition: true,
			dataType: "text",
			success: function(result){
				if(result == 'success'){
					
					// 대표 상품 이미지만 업로드
					var thumbnailData = new FormData();
					var inputFile = $('.thumbnail');
					console.log(inputFile);
					var files = inputFile[0].files;
					console.log(files);
					var pCode = $('.product_code').val()
					// *********** 파일데이터를 formdata에 추가하기
					for(var i=0; i<files.length; i++){
						thumbnailData.append("thumbnailData", files[i]);
						thumbnailData.append("pCode", pCode);
					}
					
					$.ajax({
						url: "/admin/productRegister/thumbnail",
						enctype: "multipart/form-data",
						processData: false,
						contentType: false,
						data: thumbnailData,
						type: "post",
						success: function(result){
							console.log("successfully file uploaded!!");
							//location.href="/admin/main"
						}
					});
					
				}else{
					alert('입력한 정보들을 다시 확인해주세요');
				}
			}
		})
		
		
		
		
	});
});