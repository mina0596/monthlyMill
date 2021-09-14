//변수 선언
let curPos = 0;
const IMAGE_WIDTH = 640;
const IMAGE_NUBMER = document.querySelector(".slider_list").children.length;


//실행부
window.onload = function(){
    makeSlideNumber();

    //로그인 상태일 경우 
    //showSearchTags();
    //hideSearchTextbox();
}


/* 슬라이드 */

//슬라이드 애니메이션 
function slideImage(){
    console.log("slide");
}

//슬라이드 이동 버튼
$(document).on("click", ".slider_button", function(){
	slideImage();
});

//슬라이드 번호 표시
function makeSlideNumber(){
    document.querySelector(".slider_pager__number").innerHTML = `${curPos+1}/${IMAGE_NUBMER}`;
}

//해시태그 보이기
function showSearchTags(){   
    const serchTag = document.createElement("div");
    serchTag.className = "search_tag";
    serchTag.innerHTML = `<span class="tag">${'#추천키워드'}</span>`;
    document.querySelector(".search_box").appendChild(serchTag);

}

/* 하단 메인 */

function hideSearchTextbox(){
    //로그인 시 가려지도록
    document.querySelector(".search_textbox").classList.add("hidden");
}



