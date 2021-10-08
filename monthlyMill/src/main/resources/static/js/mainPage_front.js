//변수 선언
let curSlidePos = 0;
const IMAGE_NUMBER = document.querySelector(".slider_list").children.length;
const isLogin = document.querySelector('.loginCheck').getAttribute("value");



$(function(){

    /* 상단 슬라이드 */
    $('.slider_list').slick({
         autopaly:true,
         prevArrow: $("#slidePrev"),
         nextArrow: $("#slideNext"),
    });
    // 슬라이드 번호
    makeSlideNumber();
    $('.slider_button').click(function(){
        updateSlideNumber();
    });
    
    addSearchHash();

    // 로그인 상태일 때는 value에 값 있음
    if((isLogin !== null)&&(isLogin !== "")&&(isLogin !== " ")){
        showSearchTags();
        hideSearchTextbox();
        $(".search_box").click(function(){
            location.href = '/customer/recommend/findItem';
        })
    }
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
//블러처리 해시태그 보이기
function showSearchTags(){   
    document.querySelectorAll(".hash_blur").forEach((item)=>{
        if(item.classList.contains("hash_blur")){
            item.classList.remove("hash_blur");
        }
    });
    // const serchTag = document.createElement("div");
    // serchTag.className = "search_tag";
    // serchTag.innerHTML = `<span class="tag">${'#추천키워드'}</span>`;
    // document.querySelector(".search_box").appendChild(serchTag);
}

//비회원 상태시 보여주는 문구 삭제
function hideSearchTextbox(){ 
    document.querySelector(".search_textbox_wrap").classList.add("hidden");
}




