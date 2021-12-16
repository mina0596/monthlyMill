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
            
            //인풋박스
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

            //셀렉트 박스
            thisRow.querySelectorAll(".changeable-select-siteType").forEach((cell)=>{
                const cellvalue = cell.innerHTML;
                cell.innerHTML=`<select class="changeable-input-select-siteType">
                    <option value="매장주문" ${cellvalue == "매장주문" ? "selected" : ""}>매장주문</option>
                    <option value="N스토어" ${cellvalue == "N스토어" ? "selected" : ""}>N스토어</option>
                    <option value="N예약" ${cellvalue == "N예약" ? "selected" : ""}>N예약</option>
                </select>`;
            });

            thisRow.querySelectorAll(".changeable-select-deliveryType").forEach((cell)=>{
                const cellvalue = cell.innerHTML;
                cell.innerHTML=`<select class="changeable-input-select-deliveryType">
                    <option value="방문수령" ${cellvalue == "방문수령" ? "selected" : ""}>방문수령</option>
                    <option value="직접전달" ${cellvalue == "직접전달" ? "selected" : ""}>직접전달</option>
                    <option value="퀵서비스" ${cellvalue == "퀵서비스" ? "selected" : ""}>퀵서비스</option>
                    <option value="택배,등기,소포" ${cellvalue == "택배,등기,소포" ? "selected" : ""}>택배,등기,소포</option>
                </select>`;
            });
            
            //이벤트 여부 확인?
            $(document).on("click", ".modifying", addChangeable);

            console.log("수정");

        }else{
            this.innerHTML = "수정";

            //인풋박스
            thisRow.querySelectorAll(".changeable-number").forEach((cell)=>{
                const cellvalue = cell.querySelector(".changeable-input-number").value;
                cell.innerHTML=`${cellvalue}`;
            });
            thisRow.querySelectorAll(".changeable-text").forEach((cell)=>{
                const cellvalue = cell.querySelector(".changeable-input-text").value;
                cell.innerHTML=`${cellvalue}`;
            });
            thisRow.querySelectorAll(".changeable-price").forEach((cell)=>{
                const cellvalue = cell.querySelector(".changeable-input-price").value;
                cell.innerHTML=`${cellvalue}`;
            });
            thisRow.querySelectorAll(".changeable-date").forEach((cell)=>{
                const cellvalue = cell.querySelector(".changeable-input-date").value;
                cell.innerHTML=`${cellvalue}`;
            });

            //셀렉트 박스
            thisRow.querySelectorAll(".changeable-select-siteType").forEach((cell)=>{
                const cellvalue = cell.querySelector(".changeable-input-select-siteType").value;
                cell.innerHTML=`${cellvalue}`;
            });
            thisRow.querySelectorAll(".changeable-select-deliveryType").forEach((cell)=>{
                const cellvalue = cell.querySelector(".changeable-input-select-deliveryType").value;
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
