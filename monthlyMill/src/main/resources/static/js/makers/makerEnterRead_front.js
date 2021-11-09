// 클릭 시 첨부한 이미지 보여주기=>토글방식으로
$(document).on("click", ".imgPreviewBtn", function(){
    const previewBox = $(btn).closest(".info-bodyColumn").find(".imgPreviewBox");
    previewBox.toggleClass("hidden");
});

    
// if(fileType=="pdf"){
//     pdf파일의 경우 iframe으로 보여줘야하는데 그러면 아예 처음 값 받아올때 같이 감지해서 하거나... 아니면 pdf는 미리보기 안되게 하는게 나을듯 

// const tagImg = `<iframe src="${imgSrc}" alt="${imgSrc}" width="300px" scrolling="no" class="imgPreview"></iframe>`;

