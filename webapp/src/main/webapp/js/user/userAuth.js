function userAuth() {
    var doc = document;
    var username = doc.getElementById("login").value;
    var password = doc.getElementById("password").value;
    $.ajax({
        type: "GET",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "userAuth", username: username, password: password},
        success: function (data) {
            switch (data.result) {
                case "complete":
                    doc.location.href = '../../user/main.html';
                    break;
                case "error":
                    alert("Неправильный пароль");
                    location.reload(true);
                    break;
                case "not_exist":
                    alert("Такого пользователя не существует");
                    location.reload(true);
                    break;
            }
        }
    });
}