function userDelete() {
    if (confirm("Вы точно хотите удалить данный аккаунт?") === true) {
        var doc = document;
        var pass = doc.getElementById("pass_del").value;
        $.ajax({
            type: "POST",
            url: "/RepairComp",
            dataType: "json",
            data: {requestType: "userDelete", pass_del: pass},
            success: function (data) {
                switch (data.result) {
                    case "complete":
                        alert('Аккаунт успешно удален');
                        document.location.href = '../../authorization.html';
                        break;
                    case "wrong":
                        alert('Неправильный пароль');
                        location.reload(true);
                        break;
                    default:
                        document.location.href = '../../authorization.html';
                        break;
                }
            }
        });
    }
    else {
        location.reload(true);
    }
}