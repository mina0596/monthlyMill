$(function(){
    //수정 버튼 클릭 시 input박스로 변경
    if($(".modifyRowBt").length>0){
        $(document).on("click", ".modifyRowBt", addChangeable);
    }

    function addChangeable(e){
        const thisRow = this.parentNode.parentNode;
        this.classList.toggle("modifyRowBt");
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
            
            //이벤트 여부 확인?
            $(document).on("click", ".modifying", addChangeable);

            console.log("수정");

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

            $(document).off("click", ".modifying", addChangeable);

            console.log("완료");
        }
    }

})


        /*thisRow.querySelectorAll(".changeable-select-classifyName").forEach((cell)=>{
            const cellvalue = cell.innerHTML;
            cell.innerHTML=`<select class="changeable-input-select-paymentState">
                <option value="">상품표에서 리스트 가져오기?</option>
            </select>`;
            cell.querySelector(".changeable-input-select-paymentState").value=cellvalue;
        });*/

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
