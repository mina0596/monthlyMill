$(function(){
    displayTodayDate();
});

//오늘 날짜 표시
function displayTodayDate(){
    const today = new Date();
    const todayStr = `${today.getFullYear()}년 ${today.getMonth()+1}월 ${today.getDate()}일`;

    $(".myMatch-date").text(todayStr);
};

//조회버튼 클릭 이벤트
$(document).on("click", ".myMatch-inquiryBtn", function(){
    //해당하는 매칭 페이지로 이동하게
});

//취소버튼 클릭 이벤트
$(document).on("click", ".myMatch-cancelBtn", function(){
    if(window.confirm("매칭을 취소하시겠습니까?")){
        //해당 매칭참가 취소되게

        alert("취소되었습니다.");
    }
});