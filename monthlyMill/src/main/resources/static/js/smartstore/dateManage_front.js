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

    // $(document).on("change", ".tableDate", function(){
    //     const innerCode = `
    //         <tr
    //           class="removeTr"
    //         >
    //           <td class="tdata modify">
    //             <!-- 버튼 누르면 해당 행 수정가능상태로 변경 -->
    //             <button class="modifyRowBt">수정</button>
    //           </td>
    //           <td class="tdata number" >1</td>
    //           <td class="tdata changeable-text" >
    //             완료
    //           </td>
    //           <!-- menu2 테이블에 수취인~배송형태 컬럼항목 연동 -->
    //           <td
    //             class="tdata receiverName changeable-text"
                
    //           >
    //             수취인
    //           </td>
    //           <td
    //             class="tdata productFinishTime changeable-text"
                
    //           >
    //             생산완료
    //           </td>
    //           <td
    //             class="tdata reservation changeable-text"
                
    //           >
    //             예약
    //           </td>
    //           <td
    //             class="tdata deliveryStyle changeable-text"
                
    //           >
    //             배송형태
    //           </td>
    //           <td
    //             class="tdata changeable-text classifyName"
                
    //           >
    //             답례1-3호
    //           </td>

    //           <td
    //             class="tdata quantity changeable-number"
                
    //           >
    //             1
    //           </td>

    //           <!-- 상품표 연동 -->
    //           <td
    //             class="tdata changeable-text packingMaterial"
                
    //           >
    //             포장재
    //           </td>

    //           <!-- 상품표연동+수정가능파트 -->
    //           <td
    //             class="tdata changeable-number type-duteop"
                
    //           >
    //             두텁
    //           </td>
    //           <td
    //             class="tdata changeable-number type-redbeen"
                
    //           >
    //             팥
    //           </td>
    //           <td
    //             class="tdata changeable-number type-hogo"
                
    //           >
    //             호고
    //           </td>
    //           <td
    //             class="tdata changeable-number type-nutrition"
                
    //           >
    //             영양
    //           </td>
    //           <td
    //             class="tdata changeable-number type-sasong"
                
    //           >
    //             사송
    //           </td>
    //           <td
    //             class="tdata changeable-number type-samsong"
                
    //           >
    //             삼송
    //           </td>
    //           <td
    //             class="tdata changeable-number type-isong"
                
    //           >
    //             이송
    //           </td>
    //           <td
    //             class="tdata changeable-number type-pyeshong"
                
    //           >
    //             폐송
    //           </td>
    //           <td
    //             class="tdata changeable-number type-osong"
                
    //           >
    //             오송
    //           </td>
    //           <td
    //             class="tdata changeable-number type-ssuk"
                
    //           >
    //             쑥갠
    //           </td>
    //           <td
    //             class="tdata changeable-number type-jaseol"
                
    //           >
    //             자설
    //           </td>
    //           <td
    //             class="tdata changeable-number type-danseol"
                
    //           >
    //             단설
    //           </td>
    //           <td
    //             class="tdata changeable-number type-bacseol"
                
    //           >
    //             백설
    //           </td>
    //           <td
    //             class="tdata changeable-number type-bac"
                
    //           >
    //             百
    //           </td>
    //           <td
    //             class="tdata changeable-number type-dol"
                
    //           >
    //             첫돌
    //           </td>
    //           <td
    //             class="tdata changeable-number type-hangwa"
                
    //           >
    //             한과
    //           </td>
    //           <td
    //             class="tdata changeable-number type-susu"
                
    //           >
    //             수수
    //           </td>
    //           <td
    //             class="tdata changeable-number type-cake"
                
    //           >
    //             케익
    //           </td>
    //           <td
    //             class="tdata changeable-number type-etc"
                
    //           >
    //             기타
    //           </td>

    //           <td class="tdata note changeable-text"></td>
    //         </tr>
    //     `;
    //     $(".tbody").html(innerCode);
    //     console.log("innercode change");
    // })

})

//수정 버튼 클릭 시 input박스로 변경
// function makeEditBtnFunc(){
// 	if(document.querySelectorAll(".modifyRowBt").length>0){
// 		document.querySelectorAll(".modifyRowBt").forEach((btn)=>{
// 			btn.addEventListener("click", addChangeable); 
// 		});
//         console.log("test");
// 	};
// 	console.log("end");
// };



// function addChangeable(e){
// 	const thisRow = this.parentNode.parentNode;
// 	this.classList.toggle("modifying");

// 	//현재 document에서 다시 읽어옴

// 	if(this.classList.contains("modifying")){
// 		this.innerHTML = "완료";
		
// 		thisRow.querySelectorAll(".changeable-number").forEach((cell)=>{
// 			const cellvalue = parseInt(cell.innerHTML);
// 			cell.innerHTML=`<input type="number" class="changeable-input-number" value="${cellvalue}" min="0">`;
// 		});
// 		thisRow.querySelectorAll(".changeable-text").forEach((cell)=>{
// 			const cellvalue = cell.innerHTML;
// 			cell.innerHTML=`<input type="text" class="changeable-input-text" value="${cellvalue}">`;
// 		});
// 		thisRow.querySelectorAll(".changeable-price").forEach((cell)=>{
// 			const cellvalue = parseInt(cell.innerHTML);
// 			cell.innerHTML=`<input type="number" class="changeable-input-price" value="${cellvalue}" min="0" step="10">`;
// 		});
// 		thisRow.querySelectorAll(".changeable-date").forEach((cell)=>{
// 			const cellvalue = cell.innerHTML;
// 			cell.innerHTML=`<input type="date" class="changeable-input-date" value="${cellvalue}">`;
// 		});
	

// 	}else{
// 		this.innerHTML = "수정";

// 		thisRow.querySelectorAll(".changeable-number").forEach((cell)=>{
// 			const cellvalue = cell.querySelector(".changeable-input-number").value;
// 			cell.innerHTML=`${cellvalue}`;
// 		});
// 		thisRow.querySelectorAll(".changeable-text").forEach((cell)=>{
// 			const cellvalue = cell.querySelector(".changeable-input-text").value;
// 			cell.innerHTML=`${cellvalue}`;
// 		});
// 	}
// 	console.log(e);
// }
