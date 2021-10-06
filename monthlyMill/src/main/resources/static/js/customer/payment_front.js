$(document).ready(function(){
	//각 상품별 최종가격 계산
	document.querySelectorAll("tr.result_item").forEach((item)=>{
        calculateItemSumPrice(item);
    });

	//총 결제예정금액 계산
	$(".paymentTotalPrice").text(
		parseInt($(".itemTotalPrice").text()) -
		parseInt($(".discountTotalPrice").text()) +
		parseInt($(".deliveryTotalPrice").text()) 
	);
})

// 번호입력 시 하이픈 자동추가
let autoHypenPhone = function(str){
	str = str.replace(/[^0-9]/g, '');
	let tmp = '';
	if( str.length < 4){
		return str;
	}else if(str.length < 7){
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3);
		return tmp;
	}else if(str.length < 11){
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 3);
		tmp += '-';
		tmp += str.substr(6);
		return tmp;
	}else{              
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 4);
		tmp += '-';
		tmp += str.substr(7);
		return tmp;
	}
    return str;
        
}

function calculateItemSumPrice(tableRow){
    const itemPrice = parseInt(tableRow.querySelector(".itemPrice").innerText);
    const itemQuantity = parseInt(tableRow.querySelector(".itemQuantity").innerText);
    const itemSum = tableRow.querySelector(".itemSumPrice");

    itemSum.innerText = itemPrice * itemQuantity ;
}

//결제수단 버튼 토글 이벤트
function toggleReceiptBox(toggleBtn){
	const toggleBox = document.querySelector(".payment_receipt_box");

	if(toggleBox.classList.contains("hidden")){
		toggleBox.classList.remove("hidden");
		toggleBtn.classList.add("sub");
	}else{
		toggleBox.classList.add("hidden");
		toggleBtn.classList.remove("sub");
	}
}
//현금영수증 첫 select 옵션값 따라 버튼토글 
function toggleSelect(select){
	const toggleBox = document.querySelector(".receiptNumberType");
	const toggleBox2 = document.querySelector(".recepitPhoneNumber");

	if(select.value === "none"){
		toggleBox.classList.add("hidden");
		toggleBox2.classList.add("hidden");
	}else{
		toggleBox.classList.remove("hidden");
		toggleBox2.classList.remove("hidden");
	}
}
//현금영수증 두번째 select 옵션값 따라 하단 값 변동
function changeReceiptNumberType(select){
	const changeBox = document.querySelector(".receiptApplicantPhoneNum");
	const changeinput = document.querySelector(".inputReceiptApplicantPhoneNum");

	if(select.value === "phoneNum"){
		changeBox.innerHTML = "휴대폰번호";
		changeinput.placeholder = "휴대폰번호";
		changeinput.classList.remove("phoneNum");
	}else{
		changeBox.innerHTML = "현금영수증번호";
		changeinput.placeholder = "현금영수증번호";
		changeinput.classList.remove("phoneNum");
	}
	changeinput.value = "";
}

//세부약관 토글
function toggleBoxFunc(){
    const toggleBox = document.querySelector(".toggleBox");
    const toggleBtn = document.querySelector(".toggleBtn");

    if(toggleBox.classList.contains("hidden")){
        toggleBox.classList.remove("hidden");
        toggleBtn.innerHTML = `<i class="fas fa-chevron-down"></i>`;
    }else{
        toggleBox.classList.add("hidden");
        toggleBtn.innerHTML = `<i class="fas fa-chevron-up"></i>`;
    }
}

$(document).on("keyup", ".phoneNum", function(){
	this.value = autoHypenPhone(this.value);
});

$(document).on("click", ".deposit_noBankbook_btn", function(){
	toggleReceiptBox(this);
});
$(document).on("change", ".receiptApplyType", function(){
	toggleSelect(this);
});
$(document).on("change", ".receiptNumberType", function(){
	changeReceiptNumberType(this);
});

$(document).on("click", ".toggleBtn", function(){
	toggleBoxFunc();
});


$('.gotoMatchBtn').click(function(){
	location.href="/customer/order/orderList";
})
