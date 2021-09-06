
// 번호입력 시 하이픈 자동추가
let autoHypenPhone = function(str){
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
    return str;
        
}

//약관 전체 체크
function checkAllAgreement(checkAll){
	$(".checkbox").prop('checked', checkAll.checked);
}

//세부약관 토글
function toggleBoxFunc(){
    const toggleBox = document.querySelector(".toggleBox");
    const toggleBtn = document.querySelector(".toggleBtn");

    if(toggleBox.classList.contains("hidden")){
        toggleBox.classList.remove("hidden");
        toggleBtn.innerHTML = `<i class="fas fa-chevron-down"></i>`;
    }else{
        toggleBox.classList.add("hidden");
        toggleBtn.innerHTML = `<i class="fas fa-chevron-up"></i>`;
    }
}

$(document).on("keyup", ".phoneNum", function(){
	this.value = autoHypenPhone(this.value);
});

$(document).on("click", ".checkAll", function(){
	checkAllAgreement(this);
});

$(document).on("click", ".toggleBtn", function(){
	toggleBoxFunc();
});



