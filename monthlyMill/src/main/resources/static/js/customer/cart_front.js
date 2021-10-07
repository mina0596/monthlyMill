//기능 실행
function init(){
    if(document.querySelectorAll("tr.result_item").length === 0){
        cartNoItem();
    }else{
        document.querySelectorAll("tr.result_item").forEach((item)=>{
            calculateItemSumPrice(item);
        });
        changeitemTotalNumber();

        //이벤트 리스너
        $(document).on("click", ".itemMinusBtn", function(){
            minusCartItem(this);
            calculateItemSumPrice(this.parentNode.parentNode);
        });
        $(document).on("click", ".itemPlusBtn", function(){
            plusCartItem(this);
            calculateItemSumPrice(this.parentNode.parentNode);
        });
        $(document).on("click", ".deleteItemBtn", function(){
            deleteCartItem(this);
            calculateItemTotalPrice();
        });
        $(document).on("click", ".deleteItemAllBtn", function(){
            deleteCartItemAll(this);
        });
    }
}

init();


//카트 아이템 개수합산 변경 
function changeitemTotalNumber(){
    const itmeNumberTotal = document.querySelector(".itmeNumberTotal");
    itmeNumberTotal.innerText = document.querySelectorAll("tr.result_item").length;
}

//카트 상품 수량 감소
function minusCartItem(btn){
    const itemNum = parseInt(btn.nextElementSibling.innerHTML);
    if(itemNum > 1){
        btn.nextElementSibling.innerHTML = itemNum-1;
    }
}
//카트 상품 수량 증가
function plusCartItem(btn){
    const itemNum = parseInt(btn.previousElementSibling.innerHTML);
    if(itemNum > 0){
        btn.previousElementSibling.innerHTML = itemNum+1;
    }
}


//카트 아이템 삭제
function deleteCartItem(item){
    const itemRow = item.parentNode.parentNode;
    itemRow.remove();
    changeitemTotalNumber();
    if(document.querySelector("tr.result_item") === null){
        cartNoItem();
    }
}
//카트 모든 아이템 삭제
function deleteCartItemAll(){
    const items = document.querySelectorAll("tr.result_item");
    items.forEach((item)=>{
        item.remove();
    });
    changeitemTotalNumber();
    if(document.querySelector(".result_text") == null){
        cartNoItem();
    }
}
//장바구니에 아무것도 없을 때
function cartNoItem(){
    //텍스트 창 띄우기
    const textNode = document.createElement("div");
    const text = "장바구니에 담긴 상품이 없습니다.";
    textNode.className = "result_text";
    textNode.innerHTML = text;

    document.querySelector(".cart_box").appendChild(textNode);

    //가격창 안보이게
    document.querySelector(".payment_box").classList.add("hidden");

    //버튼 비활성화
    document.querySelector(".gotoPaymentBtn").disabled = true;
}

//각 상품의 개수변동에 따른 최종가격 계산 
function calculateItemSumPrice(tableRow){
    const itemPrice = parseInt(tableRow.querySelector(".itemPrice").innerText);
    const itemQuantity = parseInt(tableRow.querySelector(".itemQuantity").innerText);
    const itemSum = tableRow.querySelector(".itemSumPrice");

    itemSum.innerText = itemPrice * itemQuantity ;
    calculateItemTotalPrice();
}
//총 상품금액 계산 
function calculateItemTotalPrice(){
    const itemSumList = document.querySelectorAll(".itemSumPrice");
    const itemTotal = document.querySelector(".itemTotalPrice");
    let total = 0;

    itemSumList.forEach((item)=>{
        total += parseInt(item.innerText);
    });
    itemTotal.innerHTML = total;
    calculatePaymentTotalPrice();
}
//총 결제예정금액 계산
function calculatePaymentTotalPrice(){
    const itemTotal = document.querySelector(".itemTotalPrice");
    const deliveryPrice = document.querySelector(".deliveryTotalPrice");
    const totalPrice = document.querySelector(".paymentTotalPrice");

    totalPrice.innerText = parseInt(itemTotal.innerText) + parseInt(deliveryPrice.innerText);
}

