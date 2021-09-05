/**
 * 
 */
$(function(){
	$('#resultBtn').click(function(){
		var checkedHashtag = $('.hashCheck').is(':checked').text();
		console.log(checkedHashtag);
		console.log('찍히나요');
	})
});