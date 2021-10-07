//가격대 직접입력 창 토글
function setPriceInputHidden(){
    if(document.querySelector("#priceInputSelf").checked){
        document.querySelector(".checkRange").classList.remove("hidden");
    }else{
        document.querySelector(".checkRange").classList.add("hidden");
    }
}

$(document).on("change", '.checkRange' , function(){
    setPriceInputHidden();
});
