
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
