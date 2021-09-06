//카트에 아이템이 없을 때


//카트 아이템 수량 변경 
function changeitemTotalNumber(){
    const itmeNumberTotal = document.querySelector(".itmeNumberTotal");
    itmeNumberTotal.innerText = document.querySelectorAll("tr.result_item").length;
}
//카트 아이템 개수 감소
function minusCartItem(btn){
    const itemNum = btn.nextElementSibling.innerHTML;
    if(itemNum > 1){
        btn.nextElementSibling.innerHTML = parseInt(itemNum)-1;
    }
}
//카트 아이템 개수 증가
function plusCartItem(btn){
    const itemNum = btn.previousElementSibling.innerHTML;
    if(itemNum > 0){
        btn.previousElementSibling.innerHTML = parseInt(itemNum)+1;
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


$(document).on("click", ".itemMinusBtn", function(){
	minusCartItem(this);
});
$(document).on("click", ".itemPlusBtn", function(){
	plusCartItem(this);
});
$(document).on("click", ".deleteItemBtn", function(){
	deleteCartItem(this);
});
$(document).on("click", ".deleteItemAllBtn", function(){
	deleteCartItemAll(this);
});

