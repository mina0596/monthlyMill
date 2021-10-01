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
		document.querySelector('.result_hash_list').innerHTML='';
		//document.getElementsByClassName('hash').remove;
		//$('.hash').remove();
		$('.result_beforeText').remove();
		$('.result_item').remove();
		html='';
		selectedTagsName = [];
		selectedTags = [];
		selectedMidClass = [];
		
		
		// ********************************** 선택한 해시태그 화면단에 뿌려주기************************
		$('.hashCheck').each(function(){
			
			if($(this).is(':checked')){
				
				selectedTagsName.push($(this).attr('id'));
				selectedTags.push($(this).val());
				selectedMidClass.push($(this).parent().parent().children().find('.hash_box__title').text());
				console.log("===============")
				console.log(selectedTagsName); 
				console.log(selectedTags); 
				console.log(selectedMidClass); 
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
			$('.result_item').remove();
			html = '';
			if(selectedTagsName.length != 0){
				//selectedTagName에 기존내용이 계속 중첩되는 오류있음.
				for(var i=0; i<selectedTagsName.length; i++){
					html += '<li class="hash">' + selectedTagsName[i] + '</li>';
				}
				$('.result_hash_list').html(html);
				
			}else{
				html = `<span>태그를 선택해주세요.</span>`;
				$('.text_box').html(html);
			}

			
			if(rcmdResult.length != 0){
				resultHtml = '';
				for(var j=0; j<rcmdResult.length; j++){
					resultHtml += `
					<tr class="result_item">
						<td>
							<span class="result_item__num">${j}</span>
						</td>
						<td>
							<img src="" alt="제품이미지" class="result_item__img">
						</td>
						<td>
							<div class="td_column">
								<span class="result_item__title">${rcmdResult[j].pName}</span>
								<input name="pCode" class="result_item_code" type="hidden" value="${rcmdResult[j].pCode}">
								<button class="result_item__nutrient readDetail">
									<i class="fas fa-search-plus"></i>
									<span>영양정보</span>
								</button>
							</div>
						</td>
						<td>
							<span class="result_item__price price_number">${rcmdResult[j].pPrice}</span>
						</td>
						<td>
							<input type="submit" class="button result_item__btnCart" value="장바구니" />
						</td>
					</tr>`;
				}
				$('.result_table_body').html(resultHtml);
			}else{
				$('.text_box').html('<span>선택하신 해시태그에 해당하는 상품이 없습니다.</span>');
			}	
		});
		
		request.fail(function( jqXHR, textStatus ) {
	 		$('.result_item').remove();
			$('.text_box').html('<span>선택하신 해시태그에 해당하는 상품이 없습니다.</span>');
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
