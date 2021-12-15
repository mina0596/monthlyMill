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
					html += '<tr class="removeTr" id=' + data[i].orderNum + "," + data[i].orderIdx + '>';
					html += '<td class="tdata modify"><button class="modifyRowBt">'+ '수정' + '</button></td>';
					html += '<td class="tdata number">' + (i+1) + '</td>';
					html += '<td class="tdata changeable-text shippingPayCheck" id="shippingPayCheck">' + data[i].shippingPayCheck + '</td>';
					html += '<td class="tdata receiverName changeable-text" id="receiverName">' + data[i].receiverName + '</td>';
					html += '<td class="tdata productFinishTime changeable-text" id="productionComp">' + data[i].productionComp + '</td>';
					html += '<td class="tdata reservation changeable-text" id="expDeliveryDate">' + data[i].expDeliveryDate + '</td>';
					html += '<td class="tdata deliveryStyle changeable-text" id="shippingAddr">' + data[i].shippingAddr + '</td>';
					html += '<td class="tdata changeable-select-classifyName classifyName" id="productName">' + data[i].productName + '</td>';
					html += '<td class="tdata quantity changeable-number" id="orderQuantity">' + data[i].orderQuantity + '</td>';
					html += '<td class="tdata packingMaterial" id="wrappingType">' + data[i].wrappingType + '</td>';
					html += '<td class="tdata changeable-number type-duteop" id="item01">' + data[i].item01 + '</td>';
					html += '<td class="tdata changeable-number type-redbeen" id="item02">' + data[i].item02 + '</td>';
					html += '<td class="tdata changeable-number type-hogo" id="item03">' + data[i].item03 + '</td>';
					html += '<td class="tdata changeable-number type-nutrition" id="item04">' + data[i].item04 + '</td>';
					html += '<td class="tdata changeable-number type-sasong" id="item05">' + data[i].item05 + '</td>';
					html += '<td class="tdata changeable-number type-samsong" id="item06">' + data[i].item06 + '</td>';
					html += '<td class="tdata changeable-number type-isong" id="item07">' + data[i].item07 + '</td>';
					html += '<td class="tdata changeable-number type-pyeshong" id="item08">' + data[i].item08 + '</td>';
					html += '<td class="tdata changeable-number type-osong" id="item09">' + data[i].item09 + '</td>';
					html += '<td class="tdata changeable-number type-ssuk" id="item10">' + data[i].item10 + '</td>';
					html += '<td class="tdata changeable-number type-jaseol" id="item11">' + data[i].item11 + '</td>';
					html += '<td class="tdata changeable-number type-danseol" id="item12">' + data[i].item12 + '</td>';
					html += '<td class="tdata changeable-number type-bacseol" id="item13">' + data[i].item13 + '</td>';
					html += '<td class="tdata changeable-number type-bac" id="item14">' + data[i].item14 + '</td>';
					html += '<td class="tdata changeable-number type-dol" id="item15">' + data[i].item15 + '</td>';
					html += '<td class="tdata changeable-number type-hangwa" id="item16">' + data[i].item16 + '</td>';
					html += '<td class="tdata changeable-number type-susu" id="item17">' + data[i].item17 + '</td>';
					html += '<td class="tdata changeable-number type-cake" id="item18">' + data[i].item18 + '</td>';
					html += '<td class="tdata changeable-number type-etc" id="item19">' + data[i].item19 + '</td>';
					html += '<td class="tdata note changeable-text" id="memo">' + data[i].memo + '</td></tr>';
					
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
				html += '<tbody class="noProductionTbody" style="text-align: center"><td colspan="25"> 생산할 품목이 없습니다. </td></tbody>';
				
				$('.removeItemTotalTr').remove();
				totHtml += '<tbody class="noItemTbody" style="text-align: center"><td colspan="18"> 생산할 품목이 없습니다. </td></tbody>';
				
			}
			
			$('#productionInfoTbody').append(html);
			$('#totalItemTbody').append(totHtml);
		} 
	})
}



/* ************************* 실 화면에서 사용되는 함수  *********************/

$(document).ready(function(){
	
	
	$(document).on('click', '.prevDateBtn', function(){
		var productionDate = $('.tableDate').val();
		var tableBody = $('.noProductionTbody');
		tableBody.css("text-align", "center");
		sendProductionDate(productionDate);
		console.log("production test");

	})
	
	$(document).on('click', '.nextDateBtn', function(){
		var tableBody = $('.noProductionTbody');
		tableBody.css("text-align", "center");
		var productionDate = $('.tableDate').val();
		sendProductionDate(productionDate);
		console.log("production test");

	})
	
	// 수정전의 데이터 담을 배열
	var orgData = [];
	
	// 수정후의 데이터 담을 배열
	var modData = [];
	
	// 변경된 데이터 정보 담을 map
	var modifyingInfo = {};
	
	// 수정버튼 클릭시 수정되기 전 데이터가져오기	
	$(document).on('click', '.modifyRowBt', function(){
		orgData = [];
		// 수정하기전 데이터
		arr = [];
		arr = $(this).parent().parent().children();
		console.log(arr);
		
		var dataNum = 0; 		// 수정이 불가능한 텍스트는 제외시키기위한 변수
		arr.each(function(){
			dataNum = dataNum + 1 ;
			console.log(orgData.length);
			if(orgData.length == 0){
				if(dataNum > 2){
				orgData.push($(this).text());
				}	
			}else{
				orgData.push($(this).val());
				console.log('llll');
			}
			
		})
		console.log(orgData);
	})
	
	
	// 수정완료 눌렀을때
	$(document).on('click', '.modifying', function(){
		modData = [];
		arr = [];
		
		// 왜... 여기는 input 으로 찾아지는걸까?
		arr = $(this).parent().parent().children().find('input');
		console.log(orgData);
		// 수정 후의 데이터 가져오기
		var dataNum = 0;
		arr.each(function(index){
			dataNum = dataNum + 1 ;
			if(dataNum > 2){
				//modData.push($(this).val());
				if(orgData[index] != $(this).val()){
					modifyingInfo.orderIdx = $(this).parent().parent('tr').attr('id');
					modifyingInfo.columnName = $(this).parent().attr('id');
					modifyingInfo.modifiedContent = $(this).val();
				}
			}
			
		});
		console.log(modData);
		console.log(modifyingInfo);
		
		
		// update 하는 쿼리 실행
		$.ajax({
			url : "/smartStore/updateOrderInfo",
			method : "post",
			data : JSON.stringify(modifyingInfo),
			dataType : "text",
			traditional : true,
			contentType : "application/json",
			success : function(data){
				console.log(data);
			}
			
		})
		
	})
	
	
})

	

