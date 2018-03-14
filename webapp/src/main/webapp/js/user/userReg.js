function userReg(username, phone, email, pass) {
    $.ajax({
        type: "POST",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "userReg", username: username, phone: phone, email: email, password: pass},
        success: function (data) {
            switch (data.result) {
                case "complete":
                    alert('Вы успешно зарегистрировались');
                    document.location.href = '../../authorization.html';
                    break;
                case "exists":
                    alert('Такой аккаунт уже существует');
                    location.reload(true);
                    break;
                case "mail_exists":
                    alert('Этот email уже используется');
                    location.reload(true);
                    break;
                default:
                    document.location.href = '../../authorization.html';
                    break;
            }
        }
    });
}