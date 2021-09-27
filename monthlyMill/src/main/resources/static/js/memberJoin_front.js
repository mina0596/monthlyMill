/* ------------ 약관동의 ------------ */

//약관 전체 체크
function checkAllAgreement(checkAll){
	$(".checkbox").prop('checked', checkAll.checked);
}

//약관동의 전체내용 모달창 띄우기
function openDetailModal(){
	let modalWrap = document.createElement("div");
	modalWrap.className = "modalWrap";
	let modalTitle = `약관제목`;
	let modalcontent = `약관내용 전문 이부분은 이후 각 내용별로 받아오기 Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequuntur illum porro dolores nobis tenetur laudantium repudiandae, repellat ipsum sunt officia ipsa, laborum dolore necessitatibus possimus reiciendis magnam suscipit deserunt. Nemo. 
	Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequuntur illum porro dolores nobis tenetur laudantium repudiandae, repellat ipsum sunt officia ipsa, laborum dolore necessitatibus possimus reiciendis magnam suscipit deserunt. Nemo.
	Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequuntur illum porro dolores nobis tenetur laudantium repudiandae, repellat ipsum sunt officia ipsa, laborum dolore necessitatibus possimus reiciendis magnam suscipit deserunt. Nemo.`;

	modalWrap.innerHTML = `
		<div class="modal">
			<div class="modal_box">
				<h3 class="modal_title">${modalTitle}</h3>
			</div>
			<div class="modal_box content">
				<p class="modal_content">
					${modalcontent}
				</p>
			</div>
			<div class="modal_box button_box">
				<button type="button" class="closeModalBtn btn small">닫기</button>
			</div>
		</div>
	`;

	$("body").prepend(modalWrap);
	scrollDisable();
}

//모달창 닫기
function closeDetailModal(){
	$(".modalWrap").remove();
	scrollAble();
}

//스크롤 막기
function scrollDisable(){
	$("html").addClass("noScroll");
}
//스크롤 막기 해제
function scrollAble(){
	$("html").removeClass("noScroll");
}

//이벤트 리스너
$(document).on("click", ".checkAll", function(){
	checkAllAgreement(this);
});
$(document).on("click", ".readDetail", function(){
	openDetailModal();
});
$(document).on("click", ".closeModalBtn", function(){
	closeDetailModal();
});

/* ------------ 기본정보 입력 ------------ */
//계좌번호 입력란에 숫자만 입력되게 처리
$(document).on("keyup", ".numberOnly", function() {
	$(this).val($(this).val().replace(/[^0-9]/g,""));
});
