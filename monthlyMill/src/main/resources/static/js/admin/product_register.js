/**
 * 
 */
$(function(){
	$('.previewBtn').click(function(){
		
		var formData = new FormData();
		var inputFile = $('.thumbnail');
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