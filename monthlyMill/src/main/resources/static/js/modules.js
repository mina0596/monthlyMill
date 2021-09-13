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

	document.querySelector("body").prepend(modalWrap);
}

export {openDetailModal};