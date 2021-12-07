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

/* 검색관련 */
//검색 조건 추가
$(document).on("click", ".addSearchBtn", function(){
    const searchBox = document.createElement("div");
    searchBox.className="searchBox";
    
    searchBox.innerHTML = `
    <button type="button" class="deleteSearchBtn">-삭제</button>
    <select name="searchKey" class="searchKey">
        <option value="">검색조건</option>
        <option value="구분명">구분명</option>
        <option value="포장재">포장재</option>
        <option value="비고">비고</option>
    </select>
    <input type="text" class="searchTextInput" name="searchValue" placeholder="검색어 입력"
        />`;

    document.querySelector(".top-searchBox").append(searchBox); 
 });

//검색 조건 삭제
$(document).on("click", ".deleteSearchBtn", function(){
    this.parentNode.remove();
});





