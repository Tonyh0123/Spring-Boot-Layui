var userId = document.getElementById("userId").innerText;
var userName = document.getElementById("userName").innerText;
$.ajax({
    type: "POST",
    url:'/testQuestion/checkTestResult',
    data: {"userId":userId, "userName":userName},
    dataType:'json',
    async: false,
    success:function(data) {
        document.getElementById("leftTime").innerHTML = data.leftTime;
        // timeeee = data.leftTime;
    }
});