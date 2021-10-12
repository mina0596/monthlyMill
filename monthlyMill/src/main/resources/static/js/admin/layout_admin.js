$(document).ready(function(){
    //gnb 불러오기
    //$("aside").load("../component/admin_gnb.html");
    $("aside").load("/admin_gnb");
});

/* gnb 사이드바 메뉴 토글 */
function toggleSubMenu(nameBox){
    const mainMenu = nameBox.parentNode;
    const toggleArrow = mainMenu.querySelector(".gnbAdmin_toggle");
    const subMenu = mainMenu.querySelector(".gnbAdmin_sub");

    const upDown = subMenu.classList.contains("hidden") ? "up" : "down";

    toggleArrow.innerHTML = `<i class="fas fa-chevron-${upDown}"></i>`;
    subMenu.classList.toggle("hidden");
}


/* 토글버튼 이벤트 리스너 */
$(document).on("click", ".gnbAdmin_main__nameBox", function(){
    toggleSubMenu(this);
});