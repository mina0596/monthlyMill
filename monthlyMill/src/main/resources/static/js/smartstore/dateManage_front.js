//날짜 변경 이벤트

//날짜 옆 버튼클릭 이벤트
//어제로 
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

//날짜 클릭 시 날짜변경 가능한 input박스로 변경
/*document.querySelector(".tableDate").addEventListener("click", clickDate);

function clickDate(){
    const tableDate = document.querySelector(".tableDate");
    const inputTableDate = document.createElement("input");
    inputTableDate.type = "date";
    inputTableDate.className = "inputTableDate";
    inputTableDate.value = tableDate.innerHTML.toString();

    tableDate.innerHTML = "";
    tableDate.append(inputTableDate);
    document.querySelector(".tableDate").removeEventListener("click", clickDate);
    document.querySelector("body").addEventListener("click", clickDateOutside);
}
//input박스를 다시 일반 텍스트로 변경
function clickDateOutside(e){
    if(document.querySelectorAll(".inputTableDate").length>0){
        const target = e.target;
        const tableDate = document.querySelector(".tableDate");
        const inputTableDate = document.querySelector(".inputTableDate");
    
        //영역 밖을 클릭한 경우
        if(target !== tableDate && target !== inputTableDate){
            tableDate.innerHTML = inputTableDate.value;
            document.querySelector("body").removeEventListener("click", clickDateOutside);
            document.querySelector(".tableDate").addEventListener("click", clickDate);
        }
    }
}*/
