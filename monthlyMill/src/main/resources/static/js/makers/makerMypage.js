/*====== 추가정보 입력 안내창 ======*/
$(document).on("click", ".mypage-infoBox-enterBtn", function(){
    //enter화면으로 넘어가도록 
});

/*====== 패스워드 재입력창 ======*/
$(document).on("click", ".mypage-infoBox-pwConfirmBtn", function(){
    //pwCheck에 패스워드 일치여부 T/F값 넣어주세요
    let pwCheck = false;
	
	var inputPw = $('.mypage-infoBox-input').val();
	
	$.ajax({
		url: "/makers/myPage/pwConfirm",
		method: "post",
		data: {inputPw : inputPw},
		dataType: "json",
		success: function(result){
			if(result.pwCheck){
		        //패스워드 일치 시 다음화면으로 넘어가게
				location.href="/makers/myPage/user/editBasicInfo";
			}else{
			    //패스워드 불일치 시
		        $(".mypage-infoBox-pwValidatingMsg").text("비밀번호가 일치하지 않습니다.");
			}
		}
	});
	
});

$(document).on("click", ".mypage-infoBox-pwCancelBtn", function(){
    //이전 페이지로
    history.back();
});



/* ============================[민아]====================================*/

/*====== 메이커스 기본정보 읽기전용화면에서 수정화면으로 이동 ======*/
$(document).on("click", ".info-updateBtn", function(){
    //이전 페이지로
    location.href="/makers/myPage/user/confirmPw";
});

