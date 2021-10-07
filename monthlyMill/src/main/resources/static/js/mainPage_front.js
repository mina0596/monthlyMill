//변수 선언
let curSlidePos = 0;
const IMAGE_NUMBER = document.querySelector(".slider_list").children.length;


$(function(){

    /* 상단 슬라이드 */
    $('.slider_list').slick({
        autopaly:true,
         prevArrow: $("#slidePrev"),
         nextArrow: $("#slideNext"),
    });

    makeSlideNumber();
    addSearchHash();

    $('.slider_button').click(function(){
        updateSlideNumber();
    });
    
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

//랜덤 해시태그 넣기
function addSearchHash(){
    const hashes = ["선물용", "행사용", "식사대용", "답례용", "데일리용", "단맛", "고소한맛", "향긋한맛", "담백한맛", "팥", "콩", "호박", "쑥", "어린이/청소년", "성인/직장인", "중장년/노인", "저칼로리", "중간칼로리", "고칼로리"];
    let randomHashes = [];
    
    while(randomHashes.length < 12){
        const randomItem = hashes.splice(Math.floor(Math.random() * hashes.length), 1)[0];
        randomHashes.push(randomItem);
    }
    randomHashes.forEach((hash)=>{
        const item = document.createElement("span");
        item.className = "hash hash_blur";
        item.innerText = hash;
        document.querySelector(".search_hash_blur").appendChild(item);
    });
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



