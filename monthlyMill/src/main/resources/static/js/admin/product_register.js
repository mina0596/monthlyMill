/**
 * 
 */
$(function(){
	// 할인 미설정시 disabled:disabled 로 설정됨.
	$('#discount_on').click(function(){
		$('.discount_number').attr('disabled', false);
	})
	$('#discount_off').click(function(){
		$('.discount_number').attr('disabled', true);
	})
	
	
	$('.previewBtn').click(function(){
		var params = {
			pCode: $('.product_code').val(),
			pName : $('.product_name').val(),
			pPrice: $('.product_price').val()
			
			
		}
		
		
		
		var formData = new FormData();
		var inputFile = $('.thumbnail');
		var subImgs = $('.subImg');
		var files = inputFile[0].files;
		console.log(files);
		
		// *********** 파일데이터를 formdata에 추가하기
		for(var i=0; i<files.length; i++){
			formData.append("uploadFile", files[i]);
		}
		

		$.ajax({
			url: "/admin/productRegister/uploadImg",
			enctype: "multipart/form-data",
			processData: false,
			contentType: false,
			data: formData,
			type: "post",
			success: function(result){
				console.log("successfully file uploaded!!");
			}
		});
		
		
	});
});