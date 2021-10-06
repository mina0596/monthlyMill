/* 공통사용 코드 분리 */

//모달창 띄우기
function openDetailModal(modalContent, modalTitle){
	let modalWrap = document.createElement("div");
	modalWrap.className = "modalWrap";

	modalWrap.innerHTML = `
		<div class="modal">
			<div class="modal_box">
				<h3 class="modal_title">${modalTitle}</h3>
			</div>
			<div class="modal_box content">
				<p class="modal_content">
					${modalContent}
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
/*
$(document).on("click", ".readDetail", function(){
	openDetailModal(modalContent);
});
*/
$(document).on("click", ".closeModalBtn", function(){
	closeDetailModal();
});