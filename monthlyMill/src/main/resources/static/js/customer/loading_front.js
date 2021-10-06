const loading_icon = document.querySelector(".loading_icon");
const endLoading = document.querySelector(".endLoading");
let matchCount = 0; 

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
    deleteNowLoading();
}

//매칭 성공 시
function matchSuccess(){
    endLoading.classList.add("successMatch");
}
//재매칭
function matchRetry(){
    endLoading.classList.add("reMatch");
    const textNodes = 
        ["재매칭은 최대 2회까지 가능합니다.", 
        "최종 매칭 실패시 결제금액이 환불됩니다."];

    textNodes.forEach((textNode)=>{
        const text = document.createTextNode(textNode);
        endLoading.appendChild(text);
    });

    matchCount ++;
}
//매칭 실패
function matchFail(){
    endLoading.classList.add("failMatch");
}

//로딩 종료
function deleteNowLoading(){
    document.querySelector(".nowLoading").innerHTML = "";
}

//입금내역 로딩 화면
function paymentLoading(){
    addNowLoading("payment")
}
//매칭 진행 시 로딩화면
function paymentLoading(){
    addNowLoading("match")
}

function addNowLoading(type){
    let loadingText = '';

    if(type === "payment"){
        loadingText = `<p>고객님의 입금내역을 확인중입니다.</p>`;
    }else{
        loadingText = `<p>고객님에게 알맞은 메이커스를 찾고 있습니다.</p>`;
    }

    const loadingCode = `
        ${loadingText}
        <p>잠시만 기다려 주세요. 약 2~3분 정도 소요됩니다.</p>
        <p>2~3분이 지난 후에 모래시계를 눌러 매칭완료를 확인하세요.</p>
        <div class="animation_box">
        <i class="far fa-hourglass loading_icon"></i>
        <div class="loadingDots">
            <div class="dot"></div>
            <div class="dot"></div>
            <div class="dot"></div>
        </div>
        </div>
    `;
    document.querySelector(".nowLoading").innerHTML = loadingCode;
}

$(function(){
	setTimeout('console.log("작동하니?")', 10000);
})


