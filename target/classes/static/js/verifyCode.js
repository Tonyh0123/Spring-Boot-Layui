
$(function () {
    
})

function createVerifyCode() {
    var email = document.getElementById("schoolManagerEmail");
    if(null != email){
        var veriFyCode;
        for (var i=0; i<4; i++){
            var randomNum = Math.round(Math.random());
            console.log(randomNum);
            veriFyCode = veriFyCode + randomNum;
            console.log(veriFyCode);
        }
    }else {
        alert("请填写邮箱！");
    }

}