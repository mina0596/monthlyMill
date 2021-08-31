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


//함수 선언
function makeSlideNumber(){
    document.querySelector(".slider_pager__number").innerHTML = `${curPos+1}/${IMAGE_NUBMER}`;
}
function showSearchTags(){
    //해시태그 파츠
    const serchTag = document.createElement("div");
    serchTag.className = "search_tag";
    serchTag.innerHTML = `<span class="tag">${'#추천키워드'}</span>`;
    document.querySelector(".search_box").appendChild(serchTag);

}
function hideSearchTextbox(){
    //로그인 시 가려지도록
    document.querySelector(".search_textbox").classList.add("hidden");
}



