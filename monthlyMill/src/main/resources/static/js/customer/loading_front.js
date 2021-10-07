const loading_icon = document.querySelector(".loading_icon");
const endLoading = document.querySelector(".endLoading");
const matchBtn = document.querySelector(".matchBtn");
const matchText = document.querySelector(".matchText");
let matchCount = 0; 

//시작 후 5초 후 재매칭 안내
$(function () {
    setTimeout('loadingComplete(false)', 5000);
});

//로딩 중 아이콘 누르면 새로고침됨
loading_icon.addEventListener("click", function(){
    window.location.href = window.location.href;
});

//로딩종료 후 화면 뜨는거(true면 success, false면 fail이나 retry)
function loadingComplete(ifMatchSuccess){
    if(ifMatchSuccess){ 
        matchSuccess();
    }else{
        if(matchCount < 3){
            matchRetry();
        }else{
            matchFail();
        }
    }
    toggleNowLoading();
}

//재매칭
function matchRetry(){
    endLoading.classList.add("reMatch");
    matchBtn.innerHTML = "재매칭";
    matchText.innerHTML = 
        `재매칭은 최대 2회까지 가능합니다.<br/> 
        최종 매칭 실패시 결제금액이 환불됩니다.`
    // matchCount ++;

    matchBtn.addEventListener("click", retryAction);
}
function retryAction(){
    toggleNowLoading();
    setTimeout('loadingComplete(true)', 5000);
}
//매칭 성공
function matchSuccess(){
    if(endLoading.classList.contains("reMatch")){
        endLoading.classList.remove("reMatch");
        matchBtn.removeEventListener("click", retryAction);
    }
    endLoading.classList.add("successMatch");
    matchBtn.innerHTML = "매칭완료";
    matchText.innerHTML = "버튼을 눌러 결과를 확인하세요."

    
    matchBtn.addEventListener("click", function(){
        setTimeout('location.href="/customer/order/orderList"', 5000);
    });
}
   
//매칭 실패
function matchFail(){
    if(endLoading.classList.contains("reMatch")){
        endLoading.classList.remove("reMatch");
        matchBtn.removeEventListener("click", retryAction);
    }
    endLoading.classList.add("failMatch");
    matchBtn.innerHTML = "매칭실패";
    matchText.innerHTML = `
        매칭에 실패했습니다. 결제금액이 환불됩니다.<br/>
        버튼을 누르면 장바구니 화면으로 돌아갑니다. 
    `
    matchBtn.addEventListener("click", function(){
        window.location.href = "/customer/cart/cartList";
    });
}
//로딩 종료 : 로딩 문구 보이지않게 & 버튼부 보이게 함. 
function toggleNowLoading(){
    document.querySelector(".nowLoading").classList.toggle("hidden");
    endLoading.classList.toggle("hidden");
}



