/*====== 추가정보 입력 안내창 ======*/
$(document).on("click", ".mypage-infoBox-enterBtn", function(){
    //enter화면으로 넘어가도록 
});

/*====== 패스워드 재입력창 ======*/
$(document).on("click", ".mypage-infoBox-pwConfirmBtn", function(){
    //pwCheck에 패스워드 일치여부 T/F값 넣어주세요
    let pwCheck = false;

    if(pwCheck){
        //패스워드 일치 시 다음화면으로 넘어가게

    }else{
        //패스워드 불일치 시
        $(".mypage-infoBox-pwValidatingMsg").text("비밀번호가 일치하지 않습니다.");
    }
});

$(document).on("click", ".mypage-infoBox-pwCancelBtn", function(){
    //이전 페이지로
    history.back();
});