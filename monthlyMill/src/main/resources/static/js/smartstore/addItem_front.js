//종류추가 버튼클릭 이벤트
document.querySelector(".addTteokTypeBtn").addEventListener("click", function(){
    const listItem = document.createElement("li");
    listItem.innerHTML =
    `<select type="text" class="manualInput tteokType-input " >
    <option value="item01">두텁</option>
    <option value="item02">팥</option>
    <option value="item03">호고</option>
    <option value="item04">영양</option>
    <option value="item05">사송</option>
    <option value="item06">삼송</option>
    <option value="item07">이송</option>
    <option value="item08">폐송</option>
    <option value="item09">오송</option>
    <option value="item10">쑥갠</option>
    <option value="item11">자설</option>
    <option value="item12">단설</option>
    <option value="item13">백설</option>
    <option value="item14">百</option>
    <option value="item15">첫돌</option>
    <option value="item16">한과</option>
    <option value="item17">수수</option>
    <option value="item18">케익</option>
    <option value="item19">기타</option>
  </select>

    <input type="number" class="manualInput quantity">
    <label for="">개</label>
    `;
    document.querySelector(".tteockTypeInputList").appendChild(listItem);
});