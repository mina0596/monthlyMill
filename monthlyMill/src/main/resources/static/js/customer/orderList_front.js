
//버튼 클릭 따라 조회기간 직접입력 가능 여부 설정하기
function setPeriodSlefDisable(isDisable){
    const periodSelfbtns= document.querySelector(".periodSelfInput").children;

    for(let btn of periodSelfbtns){
        btn.disabled = isDisable;
    }
}

$(document).on("click", 'input[name="order_period"]:not(#period_self)' , function(){
    setPeriodSlefDisable(true);
});

$(document).on("click", '#period_self' , function(){
    setPeriodSlefDisable(false);
});

//주문취소 버튼 주문번호 값넘기기
$(document).on("click", '.orderCancelBtn', function(){
    const canceldOrderNumber = $(".orderNumber").text();  
    localStorage.setItem("canceldOrderNumber", canceldOrderNumber);
})