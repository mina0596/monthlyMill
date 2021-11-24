// 품목에 대한 모든 js

$(function(){
	// ====== 품목등록버튼 ======
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
})
