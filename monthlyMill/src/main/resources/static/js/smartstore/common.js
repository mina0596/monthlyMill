//페이지공통


/* 특정영역 설정해 프린트하는 함수 (실패)
$(function(){
	function print(){
		console.log(document.all);
		if(document.all && window.print){
			window.onbeforeprint = bfp;
			window.onafterprint = afp;
			window.print();
		}
	}
	
	function bfp(){
		if(document.all){
			console.log('bfp 실행');
			contents.style.display = 'none';
			printArea.innerHTML = document.all['printArea'].innerHTML;
		}
	}
	
	function afp(){
		if(document.all){
			contents.style.display = 'block';
			printArea.innerHTML = "";
		}
	}
	
})

*/

$(function(){
	
	
	
	// ====== 수기등록버튼 ======
	$('.inputLineBtn').click(function(){
		location.href="/smartStore/addOrder/manually";
	})
	
	// ====== 로그아웃 ======
	$('.header-logoutBtn').click(function(){
		location.href = "/smartStore/logout";
	})
	
	// ====== 출력버트 =======
	$('.printBtn').click(function(){
		print();
	})	
	
})






