function userUpdateInfo() {
    var doc = document;
    var phone = doc.getElementById("phone").value;
    var firstName = doc.getElementById("first_name").value;
    var lastName = doc.getElementById("last_name").value;
    $.ajax({
        type: "POST",
        url: "/RepairComp",
        dataType: "json",
        data: {
            requestType: "userUpdateInfo", first_name: firstName,
            last_name: lastName, phone: phone
        },
        success: function (data) {
            switch (data.result) {
                case "complete":
                    location.reload(true);
                    break;
                default:
                    document.location.href = '../../authorization.html';
                    break;
            }
        }
    });
}

function userUpdatePass() {
    var doc = document;
    var oldPass = doc.getElementById("old_pass").value;
    var newPass = doc.getElementById("new_pass").value;
    var repeatPass = doc.getElementById("repeat_pass").value;
    $.ajax({
        type: "POST",
        url: "/RepairComp",
        dataType: "json",
        data: {
            requestType: "userUpdatePass", old_pass: oldPass, new_pass: newPass,
            repeat_pass: repeatPass
        },
        success: function (data) {
            switch (data.result) {
                case "complete":
                    alert('Пароль успешно изменен');
                    location.reload(true);
                    break;
                case "wrong":
                    alert('Не соответствие паролей');
                    location.reload(true);
                    break;
                case "error":
                    alert('Не правильный пароль');
                    location.reload(true);
                    break;
                default:
                    document.location.href = '../../authorization.html';
                    break;
            }
        }
    });
}