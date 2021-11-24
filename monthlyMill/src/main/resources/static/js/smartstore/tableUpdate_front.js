//검색 조건 추가
document.querySelector(".addSearchBtn").addEventListener("click", function(){
    const searchBox = document.createElement("div");
    searchBox.className="searchBox";
    
    searchBox.innerHTML = `
    <select name="" class="categorySelect">
      <option value="">검색조건</option>
      <option value="">구분명</option>
    </select>
    <input type="text" class="searchTextInput" placeholder="검색어 입력"/>`;

    document.querySelector(".top-searchBox").append(searchBox);    
})
//셀 내용 클릭 시 input박스로 변경--아직 작업중!
/*
document.querySelectorAll(".changeable").forEach((cell)=>{
    cell.addEventListener("click", function(){    
        const categoryClass = this.classList[2];
        
        this.classList.remove("changeable");
        this.innerHTML = `<input type="text" class="tdata changeable-input ${categoryClass}" value=${this.innerHTML} /><button class="inputChangeBtn">입력</button>`;

        //다시 일반 셀로 
        const changeableInputBox = this.querySelector(".changeable-input");
        console.log(changeableInputBox.value);
        console.log(this);

        this.querySelector(".inputChangeBtn").addEventListener("click", function(){  
            this.innerHTML = `${changeableInputBox.value}`;
        });
    });
});
*/
