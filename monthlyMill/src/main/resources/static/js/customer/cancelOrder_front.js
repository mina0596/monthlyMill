document.querySelector(".orderNumber").innerText = localStorage.getItem("canceldOrderNumber");

$('.cancelRequestBtn, .prevPageBtn').click(function(){
    if(localStorage.getItem("canceldOrderNumber") !== null){
        localStorage.removeItem("canceldOrderNumber");
    }
});


