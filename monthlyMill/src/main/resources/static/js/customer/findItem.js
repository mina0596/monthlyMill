/**
 * 
 */
$(function(){
	var selectedTagsName = [];
	var selectedTags = [];
	var selectedMidClass = [];
	var html = '';
	$('#resultBtn').click(function(){
		$('.result_hash_list').remove();
		$('.hashCheck').each(function(){
			if($(this).is(':checked')){
				selectedTagsName.push($(this).attr('name'));
				selectedTags.push($(this).val());
				selectedMidClass.push($(this).parent().children('.midClassName').val());
			}
		});
		var params = {
			"selectedTagsList" : selectedTags,
			"selectedMidClassList" : selectedMidClass
		};
		
		var request = $.ajax({
			url: "/recommend/sendSelectedTags",
			method: "post",
			traditional: true,
			data: JSON.stringify(params),
			contentType: "application/json", 
			dataType: "json",
		});
		
		request.done(function(data){
			if(selectedTagsName.length != 0){
				html = '<ol class="result_hash_list">';
				for(var i=0; i<selectedTagsName.length; i++){
					html += '<li class="hash">#' + selectedTagsName[i] + '</li>';
				}
				$()
			}else{
				html += '<li class="hash">태그를 선택해주세요</li>';
			}
			$('.')
		});
		
		request.fail(function( jqXHR, textStatus ) {
	 		alert( "관리자에게 문의해주세요 " + textStatus );
		});
	})
});