/************** 가입약관 **************/

//팝업창 띄우기
if($(".popupWrapper").length){
    $(".popupWrapper").load("./makerJoin_popup.html");
}

//팝업 닫기 버튼 동작
$(document).on("click", ".titlecloseBtn", function(){
    $(".popupWrapper").hide();
});

/************** 입점신청 **************/

// 사업자등록번호 입력 시 하이픈 자동추가 
let autoHypenBusinessNum = function(str){
	str = str.replace(/[^0-9]/g, '');
	let tmp = '';
	if( str.length < 4){
        return str;
	}else if(str.length < 6){
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3);
		return tmp;
	}else{              
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 2);
		tmp += '-';
		tmp += str.substr(5);
		return tmp;
	}     
}
//사업자등록번호 진위확인 API
//https://www.data.go.kr/data/15081808/openapi.do#tab_layer_detail_function

//사업자등록번호 유효성 검사 
function checkBusinessNum(value){
    //하이픈 제거 
    const registNum = value.replace(/-/gi, '').split('').map(function (item){
        return parseInt(item, 10);
    });
    const keyNum = [1,3,7,1,3,7,1,3,5];
    let msg = "";

    //검증로직 
    let sum = keyNum.reduce((acc, cur, i)=>{
        if(i<keyNum.length){
            // console.log(`${acc}+${cur}*${registNum[i]} = ${acc+cur*registNum[i]}`);
            return acc+cur*registNum[i];
        }else{
            return acc;
        }
    }, 0);

    sum = sum + Math.floor(keyNum[8]*registNum[8] / 10);
    // console.log(sum);

    //검증로직 최종 확인
    if(10 - sum%10 === registNum[9]){
        msg = "올바른 사업자등록번호입니다.";
        $(".BusinessNumValidationMsg").addClass("desc_right");

        //파일첨부 활성화
        $(".BusinessRegistFile").attr("disabled", false);
        $(".BusinessRegistFileValidationMsg").text("pdf/jpg/png 파일만 첨부가 가능합니다.");
        
    }else{
        msg = "잘못된 사업자등록번호입니다.";
        $(".BusinessNumValidationMsg").addClass("desc_wrong");

        //파일첨부 비활성화
        $(".BusinessRegistFile").attr("disabled", true);
        $(".BusinessRegistFileValidationMsg").text("사업자등록번호를 먼저 입력해주세요.");
    }
    $(".BusinessNumValidationMsg").text(msg);
};

//첨부파일 검사 
function checkRegistFile(file, msgBox){
    msgBox.removeClass("desc_right desc_wrong");
    if(file!= ""){
        const extension = file.split(".").pop();
        const checkArray = ["pdf", "jpg", "png"];

        if(!checkArray.includes(extension)){
            alert("잘못된 형식입니다.\n파일의 확장자가 pdf/jpg/png가 맞는지 확인해주세요.");
            msgBox.text("파일을 다시 등록해주세요.");
            msgBox.addClass("desc_wrong");
        }else{
            msgBox.text("파일이 등록되었습니다.");
            msgBox.addClass("desc_right");
        }
    }
}

//통신판매업신고번호 입력 시 하이픈 자동추가 
let autoHypenReportNum = function(str){
    str = str.replace(/[^0-9]/g, '');
	let tmp = '';
	if( str.length < 5){
        return str;
	}else if(str.length < 9){
		tmp += str.substr(0, 4);
		tmp += '-';
		tmp += str.substr(4);
		return tmp;
	}else{              
		tmp += str.substr(0, 4);
		tmp += '-';
		tmp += str.substr(4, 4);
		tmp += '-';
		tmp += str.substr(6);
		return tmp;
	} 
}

//통신판매업신고번호 유효성 검사 
function checkReportNum(value){

}

//이벤트 리스너
$(document).on("keyup", ".businessNum", function(){
	this.value = autoHypenBusinessNum(this.value);
    $(".BusinessNumValidationMsg").removeClass("desc_right desc_wrong");

    if(this.value.length === 12){
        checkBusinessNum(this.value);
    }else{
        $(".BusinessNumValidationMsg").text("10자리의 숫자를 입력해주세요.");
    }
});
$(document).on("keyup", ".reportNum", function(){
    this.value = autoHypenReportNum(this.value);
    // $(".reportNumValidationMsg").removeClass("desc_right desc_wrong");
});

$(document).on("change", ".attachFile", function(){
    checkRegistFile(
        $(this).val() , 
        $(this).closest("td").find(".FileValidationMsg")
    );
});


$(document).on("click", ".joinEnterNextBtn", function(){
    //미입력 정보체크 후 넘어가게, 미입력 input란에 focus맞추기
    
});
