/**
 * 
 */
$(function(){
	var selectedTagsName = [];
	var selectedTags = [];
	var selectedMidClass = [];
	var html = '';
	var resultHtml = '';
	
	$(document).on('click', '#resultBtn',  function(){
		document.getElementsByClassName('result_hash_list').remove;
		document.getElementsByClassName('hash').remove;
		$('.hash').remove();
		$('.result_beforeText').remove();
		$('.result_item').remove();
		html='';
		
		
		// ********************************** 선택한 해시태그 화면단에 뿌려주기************************
		$('.hashCheck').each(function(){
			if($(this).is(':checked')){
				selectedTagsName.push($(this).attr('id'));
				selectedTags.push($(this).val());
				selectedMidClass.push($(this).parent().parent().children().find('.hash_box__title').text());
			}
		});
		
		//*********************************** 해당하는 상품 데려오는 AJAX******************************
		var params = {
			"selectedTagsList" : selectedTags,
			"selectedMidClassList" : selectedMidClass
		};
		
		var request = $.ajax({
			url: "/customer/recommend/sendSelectedTags",
			method: "post",
			traditional: true,
			data: JSON.stringify(params),
			contentType: "application/json",
			dataType: "json"
		});
		
		request.done(function( rcmdResult ){
			if(selectedTagsName.length != 0){
				html = '<ol class="result_hash_list">';
				for(var i=0; i<selectedTagsName.length; i++){
					html += '<li class="hash">' + selectedTagsName[i] + '</li>';
				}
			}else{
				html += '<li class="hash">태그를 선택해주세요</li>';
			}
			$('.result_hash').append(html);
			
			$('.result_item').remove();
			if(rcmdResult.length != 0){
				resultHtml = '<tr class="result_item">';
				for(var j=0; j<rcmdResult.length; j++){
					resultHtml += '<td><span class="result_item__num">'+ j + '</span></td>';
					resultHtml += '<td><img src="" alt="제품이미지" class="result_item__img"></td>';
					resultHtml += '<td><div class="td_column"><span class="result_item__title">' + rcmdResult[j].pName + '</span>';
					resultHtml += '<input name="pCode" class="result_item_code" type="hidden" value="' + rcmdResult[j].pCode + '">';
					resultHtml += '<button class="result_item__nutrient">영양정보</button></div></td>';
					resultHtml += '<td><span class="result_item__price">' + rcmdResult[j].pPrice + '원' + '</span></td>';
					resultHtml += '<td><input type="submit" class="result_item__btnCart" value="장바구니"></td>';
					resultHtml += '</tr></form>';
				}
			}else{
				resultHtml += '<tr class="result_item">';
				resultHtml += '<td><span class="no_result">선택하신 해시태그에 해당하는 상품이 없습니다.</span></td>';
			}
			$('.result_table_body').append(resultHtml);
		});
		
		request.fail(function( jqXHR, textStatus ) {
	 		$('.result_item').remove();
			$('.result_table_body').append('<tr>선택하신 해시태그에 해당하는 상품이 없습니다.</tr>');
		});
		
		
		//*********************************** 장바구니******************************
		$(document).on('click', '.result_item__btnCart', function(){
			
			var param = $(this).parent().parent().children().find('.result_item_code').val();
			var pName = $(this).parent().parent().children().find('.result_item__title').text();
			var params = { 'pCode' : param };
			$.ajax({
				url: "/customer/cart/addItem",
				method: "POST",
				traditional: true,
				data: JSON.stringify(params),
				contentType: "application/json",
				dataType: "json",
				success: function(result){
					if(result.sessionCheck == 'sessionEmpty'){
						alert('로그인 후에 이용 가능합니다.');
						location.href='/login';
					}else{
						
						alert('장바구니에 ' + pName + '를 추가하였습니다.');
					}
				}
			});
			
			
		});
	})
	
	
});