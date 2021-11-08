$(document).on("click", ".passwordChangeBtn", function(){
    $(".inputPw").attr("disabled", false);
    $(".inputPw").attr("required", true);
    $(".inputPwConfirm").attr("disabled", false);
    $(".inputPwConfirm").attr("required", true);
});