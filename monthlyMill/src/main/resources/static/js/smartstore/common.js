//페이지공통
//로그아웃버튼 작동 필요

$(function(){
	// ====== 수기등록버튼 ======
	$('.inputLineBtn').click(function(){
		location.href="/smartStore/addOrder/manually";
	})
	
	// ====== 로그아웃 ======
	$('.header-logoutBtn').click(function(){
		location.href = "/smartStore/logout";
	})
})
