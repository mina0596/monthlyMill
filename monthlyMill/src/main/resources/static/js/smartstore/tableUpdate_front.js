//수정 버튼 클릭 시 input박스로 변경
if(document.querySelectorAll(".modifyRowBt").length>0){
    document.querySelectorAll(".modifyRowBt").forEach((btn)=>{
        btn.addEventListener("click", addChangeable);
    })
}

function addChangeable(e){
    const thisRow = this.parentNode.parentNode;
    this.classList.toggle("modifying");

    if(this.classList.contains("modifying")){
        this.innerHTML = "완료";
        
        thisRow.querySelectorAll(".changeable-number").forEach((cell)=>{
            const cellvalue = cell.innerHTML;
            cell.innerHTML=`<input type="number" class="changeable-input-number" value="${cellvalue}" min="0">`;
        });
        thisRow.querySelectorAll(".changeable-text").forEach((cell)=>{
            const cellvalue = cell.innerHTML;
            cell.innerHTML=`<input type="text" class="changeable-input-text" value="${cellvalue}">`;
        });
        /*thisRow.querySelectorAll(".changeable-select-paymentState").forEach((cell)=>{
            const cellvalue = cell.innerHTML;
            cell.innerHTML=`<select class="changeable-input-select-paymentState">
                <option value=""></option>
                <option value="완료">완료</option>
                <option value="퀵비착불">퀵비착불</option>
                <option value="퀵비결제확인">퀵비결제확인</option>
                <option value="미완료">미완료</option>
            </select>`;
            cell.querySelector(".changeable-input-select-paymentState").value=cellvalue;
        });*/

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

