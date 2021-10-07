//변수 선언
let curSlidePos = 0;
const IMAGE_NUMBER = document.querySelector(".slider_list").children.length;


$(document).ready(function(){

    /* 상단 슬라이드 */
    // $('.slider_list').slick({
    //     autopaly:true,
    //     //dots:true,
    //      prevArrow: $("#slidePrev"),
    //      nextArrow: $("#slideNext"),
    // });

    makeSlideNumber();

    // $('.slider_button').click(function(){
    //     updateSlideNumber();
    // });
    
    // 로그인 상태일 경우 
    // showSearchTags();
    // hideSearchTextbox();
});


//슬라이드 번호 표시
function makeSlideNumber(){
    document.querySelector('.slider_pager__number').innerHTML = `${curSlidePos+1}/${IMAGE_NUMBER}`;
}
//번호 업데이트
function updateSlideNumber(){
    curSlidePos = parseInt($('.slick-active').attr('data-slick-index'));
    document.querySelector(".slider_pager__number").innerHTML = `${curSlidePos+1}/${IMAGE_NUMBER}`;
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



