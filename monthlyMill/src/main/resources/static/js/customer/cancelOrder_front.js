document.querySelector(".orderNumber").innerText = localStorage.getItem("canceldOrderNumber");

$('.prevPageBtn').click(function(){
    localStorage.removeItem("canceldOrderNumber");
});
$('.cancelRequestBtn').click(function(){
    localStorage.removeItem("canceldOrderNumber");
});


