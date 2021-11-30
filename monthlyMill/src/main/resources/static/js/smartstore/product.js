// 품목에 대한 모든 js

$(function(){
	
	// *******************************************************************
	// ********************** 상품 구성 리스트 화면 ****************************
	// *******************************************************************
	
	// ====== 상품 구성 추가 버튼 ======
	$('.addProductBtn').click(function(){
		location.href="/smartStore/addProduct";
	})
	
	
	// ====== 검색 버튼 ======
	$('.searchBtn').click(function(){
		
	})
	
	
	
	
	
	// *******************************************************************
	// ********************** 상품 구성 등록 화면 *****************************
	// *******************************************************************
	
	// ====== 품목 등록 완료 버튼 ======
	$('.addNewProductBtn').click(function(){
		var params = {
			pCode : $('.classifyCode-input').val(),
			pName : $('.classifyName-input').val(),
			wrappingType : $('.packingMaterial-input').val(),
			pPrice : $('.price-input').val(),
			itemName : $('.tteokType-input').val(),
			itemQuantity : $('.quantity').val(),
			memo : $('.note-input').val()
		}
		
		$.ajax({
			url : "/smartStore/getNewProductInfo",
			method : "post",
			traditional : true,
			data : JSON.stringify(params),
			contentType : "application/json",
			dataType : "text",
			success : function(result){
				if(result == 'success'){
					location.href = "/smartStore/productList";
				}else{
					alert('입력한 새로운 상품정보를 다시 확인해주세요.');
				}
			}
		})
	})
	// ====== 품목 등록 취소 버튼 ======
	$('.manualCancelBtn').click(function(){
		location.href="/smartStore/productList";
	})
})
