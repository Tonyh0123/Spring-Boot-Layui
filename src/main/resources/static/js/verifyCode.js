
$(function () {
    
})

function createVerifyCode() {
    var veriFyCode;
    for (var i=0; i<4; i++){
        var randomNum = Math.round(Math.random());
        console.log(randomNum);
        veriFyCode = veriFyCode + randomNum;
        console.log(veriFyCode);
    }
}