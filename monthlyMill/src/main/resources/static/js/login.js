/**
 * 
 */
$(function(){
	var userInputId = getCookie("userInputId");
	$('.loginBtn').click(function(){
		if($('#idSaveCheck').is(':checked')){
			var userInputId = $('input:radio[name="memberId"]');
		}	
	})
});