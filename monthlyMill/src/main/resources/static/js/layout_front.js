$(document).ready(function(){
    var isMoblie = detectMobileDevice(window.navigator.userAgent);
    var testMobile = detectMobileTest();

    //header, footer 불러오기 (모바일, pc다르게)
    if(isMoblie || testMobile){
        //모바일
        loadGnbSideMenu();
        $(".gnb_side_wrapper").load("../component/mobile_sideMenu.html");
        $("header").load("../component/mobile_header.html");
    }else{
        //기타
        $("header").load("../component/header.html");
    }
    //공통
    $("footer").load("../component/footer.html");
    //나의 주문정보 전용
    $(".asideMenu").load("../component/orderAside.html");
});




//모바일 디바이스 체크
function detectMobileDevice(agent) {
    const mobileRegex = [
      /Android/i,
      /iPhone/i,
      /iPad/i,
      /iPod/i,
      /BlackBerry/i,
      /Windows Phone/i
    ]
  
    return mobileRegex.some(mobile => agent.match(mobile))
}

//테스트 환경용 함수 (실제 환경에선 주석처리 할 것.) 
function detectMobileTest(){
    console.log(window.outerWidth);
    return  ( window.outerWidth < 769 ) ? true : false ;
}

//(모바일) gnb 메뉴바 불러오기
function loadGnbSideMenu(){
    let sideWrap = document.createElement("div");
    sideWrap.className = "gnb_side_wrapper hidden";
    $("body").prepend(sideWrap);
}
//(모바일) gnb 메뉴바 불러오기
function openGnbSideMenu(){
    document.querySelector(".gnb_side_wrapper").classList.remove("hidden");
}



/*==========  이벤트 리스너 연결   ========== */

//(공통) header gnb 준비중 페이지
$(document).on("click", ".noPage", function(){
    alert('준비중입니다.');
});

//(모바일) gnb 메뉴버튼 이벤트 연결
$(document).on("click", ".gnbSideMenuBtn", function(){
    openGnbSideMenu();  
});

//(모바일)닫기 버튼 이벤트 리스너 추가
$(document).on("click", ".gnb_side__closeBtn", function(){
    document.querySelector(".gnb_side_wrapper").classList.add("hidden");
});
