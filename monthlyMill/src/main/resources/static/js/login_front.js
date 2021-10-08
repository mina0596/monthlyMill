const idSaveCheckbox = document.querySelector("#idSaveCheck");
const inputId = document.querySelector(".inputId");
const loginBtn = document.querySelector(".loginBtn");

//이건...매칭 테스트 시나리오용
localStorage.setItem("matchScenarioNum", 0);

//아이디 저장
function saveId(){
    if(idSaveCheckbox.checked){
        localStorage.setItem("ID", inputId.value);
        localStorage.setItem("IDsaveCheck", true);
    }else{
        localStorage.setItem("ID", "");
        localStorage.setItem("IDsaveCheck", false);
    }
}

//저장된 아이디 불러오기
function loadSavedId(){
    const loadId = localStorage.getItem("ID");
    const loadIdSaveChecked = localStorage.getItem("IDsaveCheck");

    if(loadId !== null && loadIdSaveChecked){
        inputId.value = loadId;
        idSaveCheckbox.checked = loadIdSaveChecked;
    }
}

loadSavedId();
loginBtn.addEventListener("click", saveId);


