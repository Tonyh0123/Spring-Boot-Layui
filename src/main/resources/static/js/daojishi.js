var userId = document.getElementById("userId").innerText;
var userName = document.getElementById("userName").innerText;
var recordId = "";
var checkRolePermission = document.getElementById("checkThat").innerText;
if(checkRolePermission!='2'){
    window.location.href="/myError"
}else {
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
}