/**
 * 
 */
$(function(){
		$('#sameWithOrder').change(function(){
			if($(this).is(':checked')){
				$('.inputReceiverName').val($('.inputOrdererName').val());
				$('.inputReceiverPhoneNum').val($('.inputOrdererPhoneNum').val());
				$('.inputReceiverAdress').val($('.userAddr').val());
			}else{
				$('.inputReceiverName').val('');
				$('.inputReceiverPhoneNum').val('');
				$('.inputReceiverAdress').val('');
			}
		})
	
	
});