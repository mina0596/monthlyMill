/*====== 추가정보 입력 안내창 ======*/
$(document).on("click", ".mypage-infoBox-enterBtn", function(){
    //enter화면으로 넘어가도록 
});


/* ====== 패스워드 확인하는 함수 ======*/
function pWcheck(inputPw, inputUrl){
    let pwCheck = false;
	$.ajax({
		url: "/makers/myPage/pwConfirm",
		method: "post",
		data: {inputPw : inputPw},
		dataType: "json",
		success: function(result){
			if(result.pwCheck){
		        //패스워드 일치 시 다음화면으로 넘어가게
				pwCheck = true;
				location.href=inputUrl;
			}else{
			    //패스워드 불일치 시
		        $(".mypage-infoBox-pwValidatingMsg").text("비밀번호가 일치하지 않습니다.");
			}
		}
	});
}

/*====== 패스워드 재입력창 ======*/
$(document).on("click", ".mypage-infoBox-pwConfirmBtn", function(){
    //pwCheck에 패스워드 일치여부 T/F값 넣어주세요
	
	var inputPw = $('.mypage-infoBox-input').val();
	var inputUrl = "/makers/myPage/user/editBasicInfo";
	pWcheck(inputPw, inputUrl);
	
});

$(document).on("click", ".mypage-infoBox-pwCancelBtn", function(){
    //이전 페이지로
    history.back();
});



/* ============================[민아]====================================*/

/*====== 메이커스 기본정보 읽기전용화면에서 비번확인 화면으로 이동 ======*/
$(document).on("click", ".info-updateBtn", function(){
    
    location.href="/makers/myPage/user/confirmPw";
});

/*====== 메이커스 입점정보 읽기전용화면에서 비번확인 화면으로 이동 ======*/
$(document).on("click", ".info-updateBtn", function(){
   
    location.href="/makers/myPage/store/confirmPw";
});

$(function(){
	var enterCheck = $('.enterCheck').val();
	if(enterCheck == 'Y'){
		// 입정승인이 승인일 경우
	}
})
