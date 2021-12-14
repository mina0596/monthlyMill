//날짜 변경 이벤트

//날짜 옆 버튼클릭 이벤트
//어제로 
$(function(){
    document.querySelector(".prevDateBtn").addEventListener("click", function(){
        const tableDate = document.querySelector(".tableDate");
        const nowDate = new Date(tableDate.value);   
        const prevDate = new Date(nowDate.setDate(nowDate.getDate() - 1));

        tableDate.value = 
        `${prevDate.getFullYear()}-${prevDate.getMonth()+1}-${("0"+prevDate.getDate()).slice(-2)}`;
    });

    //다음날로
    document.querySelector(".nextDateBtn").addEventListener("click", function(){
        const tableDate = document.querySelector(".tableDate");
        const nowDate = new Date(tableDate.value);   
        const nextDate = new Date(nowDate.setDate(nowDate.getDate() + 1));

        tableDate.value = `${nextDate.getFullYear()}-${nextDate.getMonth()+1}-${("0"+nextDate.getDate()).slice(-2)}`;
    });

})