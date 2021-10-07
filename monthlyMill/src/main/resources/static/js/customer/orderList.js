/**
 * 
 */
$(function(){
	
	// ************** 주문 삭제 양식 ***************
	$('.result_item').remove();
	var html ='';
	var orderNum = $('.orderNumber').text();
	var totalRefundPrice = 0;
	
	$.ajax({
		url: "/customer/order/getCancelInfo",
		method: "post",
		data: { orderNum : orderNum },
		dataType: "json",
		success: function(result){
			
			if(result.length > 0){
				$('.ordererName').text(result[0].mName);
				$('.ordererPhoneNum').text(result[0].mPhone);
				html = '<tr class="result_item">';
				for(var i=0; i<result.length; i++){
					html += '<td><span class="result_item num">' + (i+1) +'</span></td>';
					html += '<td><img src="" alt="제품이미지" class="result_item img" /></td>';
					html += '<td><div class="result_item info">';
					html += '<span class="result_item title">' + result[i].pName + '</span>';
					html += '<div><span class="result_item price_number">' + result[i].pPrice + '</span></div></div>';
					html += '</td>';
					html += '<td><span class="result_delivery">택배배송</span></td>';
					html += '<td><span class="result_item itemNumber">' + result[i].pCount + '</span></td>';
					html += '<td><span class="result_item total_price_number">' + (result[i].pCount * result[i].pPrice) + '</span></td>';
					html += '<td></td>';
					html += '</tr>';
					totalRefundPrice += (result[i].pCount * result[i].pPrice);
				}
				$('.result_itemList').append(html);
				console.log(totalRefundPrice);
				$('.refund_price_number').text(totalRefundPrice + '원');
			}
		}
		
	})
	

	
});