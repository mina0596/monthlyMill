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
	var urlAddr = document.location.href;
	var ogn = document.location.origin;
	urlAddr = urlAddr.replace(ogn + '/', '');
	/* ========== 기본정보 수정시 ===========*/
	if(urlAddr == 'makers/myPage/user/confirmPw'){
		var inputPw = $('.mypage-infoBox-input').val();
		var inputUrl = "/makers/myPage/user/editBasicInfo";
		pWcheck(inputPw, inputUrl);
	/* ========== 입점정보 수정시 ===========*/
	}else if(urlAddr == 'makers/myPage/store/confirmPw'){
		var inputPw = $('.mypage-infoBox-input').val();
		var inputUrl = "/makers/myPage/user/editStoreInfo";
		pWcheck(inputPw, inputUrl);
		
	}
	
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

/* ================ 입점정보============*/

/*====== 메이커스 입점정보 읽기전용화면에서 비번확인 화면으로 이동 ======*/
$(document).on("click", ".store-updateBtn", function(){
   
    location.href="/makers/myPage/store/confirmPw";
});

$(function(){
	var enterCheck = $('.enterCheck').val();
	if(enterCheck == 'Y'){
		// 입정승인이 승인일 경우
		$('.enterApproval').addClass('approved');
	}else if(enterCheck == 'N'){
		// 입점승인이 미승인일 경우
		$('.enterApproval').attr('class', 'enterApproval');
	}
	$('#sameNameCheck').change(function(){
		if($(this).is(':checked')){
			$('.ownerName').val($('.makersName').val());
		}else{
			$('.ownerName').val('');
		}
	});
})
// 입점정보 수정 완료 후
$('.storeUpdateBtn').click(function(){
	$('#enterStore').submit();
})


/*================== 추가정보 ==============================*/
$('.addInfoBtn').click(function(){
	location.href="/makers/myPage/user/regAdditionalInfo";
})
$('.regAddInfoBtn').click(function(){
	$('#setAddInfo').submit();
})






