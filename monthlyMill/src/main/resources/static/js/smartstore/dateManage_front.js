//날짜 변경 이벤트

//날짜 옆 버튼클릭 이벤트
//어제로 
document.querySelector(".prevDateBtn").addEventListener("click", function(){
    const tableDate = document.querySelector(".tableDate");
    const nowDate = new Date(tableDate.value);   
    const prevDate = new Date(nowDate.setDate(nowDate.getDate() - 1));

    tableDate.value = 
    `${prevDate.getFullYear()}-${prevDate.getMonth()+1}-${("0"+prevDate.getDate()).slice(-2)}`;
    makeEditBtnFunc();
});
//다음날로
document.querySelector(".nextDateBtn").addEventListener("click", function(){
    const tableDate = document.querySelector(".tableDate");
    const nowDate = new Date(tableDate.value);   
    const nextDate = new Date(nowDate.setDate(nowDate.getDate() + 1));

    tableDate.value = `${nextDate.getFullYear()}-${nextDate.getMonth()+1}-${("0"+nextDate.getDate()).slice(-2)}`;
    makeEditBtnFunc();
});

//수정 버튼 클릭 시 input박스로 변경
function makeEditBtnFunc(){
    if(document.querySelectorAll(".modifyRowBt").length>0){
        document.querySelectorAll(".modifyRowBt").forEach((btn)=>{
            btn.addEventListener("click", addChangeable);
        });
        console.log("test");
    }
    console.log("end");
}

function addChangeable(e){
    const thisRow = this.parentNode.parentNode;
    this.classList.toggle("modifying");

    //현재 document에서 다시 읽어옴

    if(this.classList.contains("modifying")){
        this.innerHTML = "완료";
        
        thisRow.querySelectorAll(".changeable-number").forEach((cell)=>{
            const cellvalue = parseInt(cell.innerHTML);
            cell.innerHTML=`<input type="number" class="changeable-input-number" value="${cellvalue}" min="0">`;
        });
        thisRow.querySelectorAll(".changeable-text").forEach((cell)=>{
            const cellvalue = cell.innerHTML;
            cell.innerHTML=`<input type="text" class="changeable-input-text" value="${cellvalue}">`;
        });
        thisRow.querySelectorAll(".changeable-price").forEach((cell)=>{
            const cellvalue = parseInt(cell.innerHTML);
            cell.innerHTML=`<input type="number" class="changeable-input-price" value="${cellvalue}" min="0" step="10">`;
        });
        thisRow.querySelectorAll(".changeable-date").forEach((cell)=>{
            const cellvalue = cell.innerHTML;
            cell.innerHTML=`<input type="date" class="changeable-input-date" value="${cellvalue}">`;
        });
       

    }else{
        this.innerHTML = "수정";

        thisRow.querySelectorAll(".changeable-number").forEach((cell)=>{
            const cellvalue = cell.querySelector(".changeable-input-number").value;
            cell.innerHTML=`${cellvalue}`;
        });
        thisRow.querySelectorAll(".changeable-text").forEach((cell)=>{
            const cellvalue = cell.querySelector(".changeable-input-text").value;
            cell.innerHTML=`${cellvalue}`;
        });
    }
    console.log(e);
}

