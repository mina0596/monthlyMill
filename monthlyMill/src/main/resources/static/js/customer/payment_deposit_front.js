$(document).on("click", ".checkRequired", function(){
    if(this.checked){
        console.log($(".confirmBtn").disabled);
        $(".confirmBtn").prop("disabled", false);
    }else{
        $(".confirmBtn").prop("disabled", true);
    }
});
