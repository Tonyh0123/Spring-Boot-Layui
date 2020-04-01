var userId = document.getElementById("userId").innerText;
var userName = document.getElementById("userName").innerText;
var recordId = "";
$.ajax({
    type: "POST",
    url:'/testQuestion/checkTestResult',
    data: {"userId":userId, "userName":userName},
    dataType:'json',
    async: false,
    success:function(data) {
        document.getElementById("leftTime").innerHTML = data.leftTime;
        recordId = data.recordId;
    }
});