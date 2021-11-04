/**
 * 
 */
$(function(){
	$('.joinEnterNextBtn').click(function(){
		
		
		
		var params = {
			'sellerType' : $('.sellerType').val(),
			'companyName' : $('input[name="companyName"]').val(),
			'businessNum' : $('.businessNum').val(),
			'ownerName' :$('.ownerName').val(),
			'storeOpenDate' : $('.storeOpenDate').val(),
			'businessAddr' : $('input[name="businessAddr"]').val(),
			'sellerClass' : $('.sellerClass').val(),
			'businessStatus' : $('input[name="businessStatus"]').val(),
			'businessType' : $('input[name="businessType"]').val(),
			'salesNum' : $('input[name="salesNum"]').val(),
			'repItemCate' : $('select[name="repItemCate"]').val()			
		}
		
		$.ajax({
			url : "/join/makersJoin/makersStoreInfo",
			method : "POST",
			data : JSON.stringify(params),
			contentType: 'application/json',
			traditional: true,
			dataType: 'text',
			success: function(result){
				$('#enterStore').submit();
			}
		})
		
	});
})