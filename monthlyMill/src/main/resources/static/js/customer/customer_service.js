// 고객센터

$(function(){
	
	// *******************************
	// ======= 고객 문의글 상세 화면 ======
	// *******************************
	
	
	if($('.detail-privateCheck').val() == '공개'){
		$('.post-title').removeClass('postPrivate');
	}else if($('.detail-privateCheck').val() == '비공개'){
		$('.post-title').addClass('postPrivate');
	}
	
	
	// 관리자 답변 없앰
	$('.post-reply').remove();
	
	
	// 첨부파일 없애기
	if($('.detail-attachedFile').val() == null || $('.detail-attachedFile').val() == ''){
		$('.post-img').remove();
	}
		
		
	// 목록으로 버튼
	$('.gotoListBtn').click(function(){
		location.href="/customer/service/list";
	})
	
		
	// *******************************
	// ======= 고객 문의글 리스트 화면 ======
	// *******************************
	
	
	// 비공개글이면 자물쇠 그려지는 설정
	$(document).ready(function(){
		$('.post-privateCheck').each(function(){
			if($(this).val() == '비공개'){
				$(this).parent().addClass("postPrivate");
			}else if($(this).val() == '공개'){
				$(this).parent().removeClass("postPrivate");
			}
		})
	})
	
	// *******************************
	// ======= 문의 글쓰기 버튼 ======
	// *******************************
	
	
	// 로그인시에만 가능함
	$('.writeAskBtn').click(function(){
		var loginMId = $('.loginMId').val();
		if(loginMId == null || loginMId == ''){
			alert('로그인 후에 이용해주세요.');
		}else{
			location.href="/customer/service/write";
		}
	});
	
	// ======= 문의글 완료후 등록 버튼 ======
	$('.postBtn').click(function(){
		var submitFlag = true;
		if($('.post-category').val() == '' || $('.post-category').val() == undefined){
			alert('문의 유형을 선택해주세요.');
			submitFlag = false;
		}
		
		// 문의 유형을 선택했을시에만 가능
		if(submitFlag){
			var params = {
				csCategory : $('.post-category').val(),
				csTitle : $('.post-inputTitle').val(),
				csContent : $('.post-inputText').val(),
				csPublic : $('input[name="publicSet"]:checked').val(),
				loginMId : loginMId = $('.loginMId').val()
			};
			
			$.ajax({
				url: "/customer/service/write",
				method: "post",
				traditional: true,
				data: JSON.stringify(params),
				contentType: "application/json",
				dataType: "text",
				success: function(result){
					if(result){
						location.href = "/customer/service/list";
					}else{
						alert('작성한 내용을 빠짐없이 적었는지 확인해주세요.');
					}
				}
			})
		}
	})
})

