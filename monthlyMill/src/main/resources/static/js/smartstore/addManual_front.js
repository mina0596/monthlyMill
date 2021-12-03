// 연락처 번호입력 시 하이픈 자동추가
const autoHypenPhone = function(str){
	str = str.replace(/[^0-9]/g, '');
	let tmp = '';
	if( str.length < 4){
		return str;
	}else if(str.length < 7){
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3);
		return tmp;
	}else if(str.length < 11){
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 3);
		tmp += '-';
		tmp += str.substr(6);
		return tmp;
	}else{              
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 4);
		tmp += '-';
		tmp += str.substr(7);
		return tmp;
	}      
}

$(document).on("keyup", ".purchaserPhone-input", function(){
	this.value = autoHypenPhone(this.value);
});
$(document).on("keyup", ".receiverPhone-input", function(){
	this.value = autoHypenPhone(this.value);
});



	


//주문종류 선택 시: 희망일 시간표시 변경
$(document).on("change", ".orderType-input", function(){
  const thisListBox = this.parentNode.parentNode;
  if(this.value==="N스토어"){
    if(thisListBox.querySelector(".deliveryDateTime-input").classList.contains("hidden")){
      thisListBox.querySelector(".deliveryDatePart-input").classList.remove("hidden");
    }
    if(!thisListBox.querySelector(".deliveryDateTime-input").classList.contains("hidden")){
      thisListBox.querySelector(".deliveryDateTime-input").classList.add("hidden");
    }
  }else{
    if(thisListBox.querySelector(".deliveryDateTime-input").classList.contains("hidden")){
      thisListBox.querySelector(".deliveryDateTime-input").classList.remove("hidden");
    }
    if(!thisListBox.querySelector(".deliveryDateTime-input").classList.contains("hidden")){
      thisListBox.querySelector(".deliveryDatePart-input").classList.add("hidden");
    }
  }
});


/* 상품 추가 버튼 클릭 이벤트 */
//이부분 기존 element에서 가져올수있도록 변경
let ManualListnumber = document.querySelectorAll(".addManualListItemBoxBtn").length+1;

document.querySelector(".addManualListItemBoxBtn").addEventListener("click", function(){
    const manualListItem = document.createElement("ul");
    manualListItem.className = "manualListItem";
    //발주품목은 화면내에서 가져오기

    manualListItem.innerHTML = `
              <h5 class="manualListTitle">
                <div></div>
                <div>
                  <span>상품</span>
                  <span class="manualList-number">${ManualListnumber}</span>
                </div>
                <button class="deleteManualListItemBoxBtn">상품 삭제</button>
              </h5>
              <li class="manualList">
                <span class="manualTh">수취인명</span>
                <input type="text" class="manualInput receiverName-input" />
              </li>
              <li class="manualList">
                <span class="manualTh">수취인연락처</span>
                <input
                  type="text"
                  class="manualInput receiverPhone-input"
                  placeholder="전화번호입력"
                  maxlength="13"
                />
              </li>
              <li class="manualList">
                <span class="manualTh">상품명</span>
                <input type="text" class="manualInput itemName-input" />
              </li>
              <li class="manualList">
                <span class="manualTh">발주품목</span>
                <select class="manualInput orderItemList-input">
                  ${document.querySelector(".orderItemList-input").innerHTML}
                </select>
              </li>

              <li class="manualList">
                <span class="manualTh">수량</span>
                <div class="manualInput-unitBox">
                  <input
                    type="number"
                    class="manualInput quantity-input"
                    min="0"
                  /><span> 개</span>
                </div>
              </li>
              <li class="manualList">
                <span class="manualTh">상품별 총 주문금액</span>
                <span class="manualInput eachTotalPrice-input price_number"
                  >0</span
                >
              </li>
              <li class="manualList">
                <span class="manualTh">옵션정보</span>
                <input type="text" class="manualInput optionInfo-input" />
              </li>
              <li class="manualList">
                <span class="manualTh">배송지</span>
                <input
                  type="text"
                  class="manualInput deliveryDestination-input"
                />
              </li>
              <li class="manualList">
                <span class="manualTh">배송메세지</span>
                <input type="text" class="manualInput deliveryMsg-input" />
              </li>
              <li class="manualList">
                <span class="manualTh">배송비</span>
                <div class="manualInput-unitBox">
                  <input
                    type="number"
                    class="manualInput deliveryCharge-input"
                    step="10"
                    min="0"
                  />
                  <span>원</span>
                </div>
              </li>
              <li class="manualList">
                <span class="manualTh">주문종류</span>
                <select class="manualInput orderType-input">
                  <option value="N스토어">N스토어</option>
                  <option value="N예약">N예약</option>
                  <option value="매장주문">매장주문</option>
                </select>
              </li>
              <li class="manualList">
                <span class="manualTh">희망일</span>
                <div>
                  <input type="date" class="manualInput deliveryDate-input" />
                  <select class="manualInput deliveryDatePart-input">
                    <option value="1부">1부</option>
                    <option value="2부">2부</option>
                  </select>
                  <input
                    type="time"
                    class="manualInput deliveryDateTime-input hidden"
                    step="1800"
                  />
                </div>
              </li>
            `;
            document.querySelector(".manualListItemBox").appendChild(manualListItem);
            
            ManualListnumber++;
});

/* 상품 삭제 버튼 클릭 이벤트 */
$(document).on("click", ".deleteManualListItemBoxBtn", function(){
  const thisItem = this.parentNode.parentNode;
  thisItem.remove();
});