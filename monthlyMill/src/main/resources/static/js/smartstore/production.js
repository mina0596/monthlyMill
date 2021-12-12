/*
	생상일지에 대한 js
*/


// 날짜 정보받아서 생산일지정보 보내주는 함수

function sendProductionDate(productionDate){
	$.ajax({
		url : "/smartStore/getProductionInfoByDate",
		data : {productionDate : productionDate},
		method : "post",
		dataType : "json",
		success : function(data){
			console.log(data);
			var html = '';
			var itemNum = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
			var totHtml = '';
			$('.removeTr').remove();
			$('.removeItemTotalTr').remove();
			$('.noProductionTbody').remove();
			$('.noItemTbody').remove();
			if(data.length > 0){
				for(var i=0; i < data.length; i++){
					html += '<tr class="removeTr">';
					html += '<td class="tdata modify"><button class="modifyRowBt">'+ '수정' + '</button></td>';
					html += '<td class="tdata number">' + i+1 + '</td>';
					html += '<td class="tdata changeable-text">' + data[i].shippingPayCheck + '</td>';
					html += '<td class="tdata receiverName changeable-text">' + data[i].receiverName + '</td>';
					html += '<td class="tdata productFinishTime changeable-text">' + data[i].productionComp + '</td>';
					html += '<td class="tdata reservation changeable-text">' + data[i].expDeliveryDate + '</td>';
					html += '<td class="tdata deliveryStyle changeable-text">' + data[i].shippingAddr + '</td>';
					html += '<td class="tdata changeable-select-classifyName classifyName">' + data[i].productName + '</td>';
					html += '<td class="tdata quantity changeable-number">' + data[i].orderQuantity + '</td>';
					html += '<td class="tdata packingMaterial">' + data[i].wrappingType + '</td>';
					html += '<td class="tdata changeable-number type-duteop">' + data[i].item01 + '</td>';
					html += '<td class="tdata changeable-number type-redbeen">' + data[i].item02 + '</td>';
					html += '<td class="tdata changeable-number type-hogo">' + data[i].item03 + '</td>';
					html += '<td class="tdata changeable-number type-nutrition">' + data[i].item04 + '</td>';
					html += '<td class="tdata changeable-number type-sasong">' + data[i].item05 + '</td>';
					html += '<td class="tdata changeable-number type-samsong">' + data[i].item06 + '</td>';
					html += '<td class="tdata changeable-number type-isong">' + data[i].item07 + '</td>';
					html += '<td class="tdata changeable-number type-pyeshong">' + data[i].item08 + '</td>';
					html += '<td class="tdata changeable-number type-osong">' + data[i].item09 + '</td>';
					html += '<td class="tdata changeable-number type-ssuk">' + data[i].item10 + '</td>';
					html += '<td class="tdata changeable-number type-jaseol">' + data[i].item11 + '</td>';
					html += '<td class="tdata changeable-number type-danseol">' + data[i].item12 + '</td>';
					html += '<td class="tdata changeable-number type-bacseol">' + data[i].item13 + '</td>';
					html += '<td class="tdata changeable-number type-bac">' + data[i].item14 + '</td>';
					html += '<td class="tdata changeable-number type-dol">' + data[i].item15 + '</td>';
					html += '<td class="tdata changeable-number type-hangwa">' + data[i].item16 + '</td>';
					html += '<td class="tdata changeable-number type-susu">' + data[i].item17 + '</td>';
					html += '<td class="tdata changeable-number type-cake">' + data[i].item18 + '</td>';
					html += '<td class="tdata changeable-number type-etc">' + data[i].item19 + '</td>';
					html += '<td class="tdata note changeable-text">' + data[i].memo + '</td></tr>';
					
					itemNum[0] += data[i].item01;					itemNum[1] += data[i].item02;
					itemNum[2] += data[i].item03;					itemNum[3] += data[i].item04;
					itemNum[4] += data[i].item05;					itemNum[5] += data[i].item06;
					itemNum[6] += data[i].item07;					itemNum[7] += data[i].item08;
					itemNum[8] += data[i].item09;					itemNum[9] += data[i].item10;
					itemNum[10] += data[i].item11;					itemNum[11] += data[i].item12;
					itemNum[12] += data[i].item13;					itemNum[13] += data[i].item14;
					itemNum[14] += data[i].item15;					itemNum[15] += data[i].item16;
					itemNum[16] += data[i].item17;					itemNum[17] += data[i].item18;
					itemNum[18] += data[i].item19;
				}
				totHtml += '<tr class="removeItemTotalTr">';
				totHtml += '<td class="tdead type">품목별 합계</td>';
				totHtml += '<td tresult totalAmount-duteop">' + itemNum[0] + '</td>';
				totHtml += '<td tresult totalAmount-redbeen">' + itemNum[1] + '</td>';
				totHtml += '<td tresult totalAmount-hogo">' + itemNum[2] + '</td>';
				totHtml += '<td tresult totalAmount-nutrition">' + itemNum[3] + '</td>';
				totHtml += '<td tresult totalAmount-sasong">' + itemNum[4] + '</td>';
				totHtml += '<td tresult totalAmount-samsong">' + itemNum[5] + '</td>';
				totHtml += '<td tresult totalAmount-isong">' + itemNum[6] + '</td>';
				totHtml += '<td tresult totalAmount-pyeshong">' + itemNum[7] + '</td>';
				totHtml += '<td tresult totalAmount-osong">' + itemNum[8] + '</td>';
				totHtml += '<td tresult totalAmount-ssuk">' + itemNum[9] + '</td>';
				totHtml += '<td tresult totalAmount-jaseol">' + itemNum[10] + '</td>';
				totHtml += '<td tresult totalAmount-danseol">' + itemNum[11] + '</td>';
				totHtml += '<td tresult totalAmount-bacseol">' + itemNum[12] + '</td>';
				totHtml += '<td tresult totalAmount-bac">' + itemNum[13] + '</td>';
				totHtml += '<td tresult totalAmount-dol">' + itemNum[14] + '</td>';
				totHtml += '<td tresult totalAmount-hangwa">' + itemNum[15] + '</td>';
				totHtml += '<td tresult totalAmount-susu">' + itemNum[16] + '</td>';
				totHtml += '<td tresult totalAmount-cake">' + itemNum[17] + '</td>';
				totHtml += '<td tresult totalAmount-etc">' + itemNum[18] + '</td></tr>';
			}else{
				console.log('????');
				$('.removeTr').remove();
				/* 왜 글씨가 가운데 정렬이 안될까요......?*/
				html += '<tbody class="noProductionTbody" style="text-align: center;"><td colspan="25" style="text-align: center;"> 생산할 품목이 없습니다. </td></tbody>';
				
				$('.removeItemTotalTr').remove();
				totHtml += '<tbody class="noItemTbody" style="text-align: center;"><td colspan="18" style="text-align: center;"> 생산할 품목이 없습니다. </td></tbody>';
				
			}
			
			$('#productionInfoTbody').append(html);
			$('#totalItemTbody').append(totHtml);
		} 
	})
}



/* ************************* 실 화면에서 사용되는 함수  *********************/
$(function(){
	
	$('.prevDateBtn').click(function(){
		var productionDate = $('.tableDate').text();
		sendProductionDate(productionDate);
	})
	
	$('.nextDateBtn').click(function(){
		var productionDate = $('.tableDate').text();
		sendProductionDate(productionDate);
	})
	
	// 수정된 데이터가져오기	
	$('.tdata changeable-number').change(function(){
		console.log($(this));
	})
	
	
	// 수정완료 눌렀을때
	$('.modifyRowBt modifying').click(function(){
	})
	
})




