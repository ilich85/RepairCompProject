function inputValidation() {
    var doc = document;
    doc.getElementById("log_reg").onkeypress = function (event) {
        event = event || window.event;
        return (event.charCode > 47 && event.charCode < 58) ||
            (event.charCode > 96 && event.charCode < 123);
    };
    doc.getElementById("mail_reg").onkeypress = function (event) {
        event = event || window.event;
        return (event.charCode == 46) || (event.charCode == 64) ||
            (event.charCode > 47 && event.charCode < 58) ||
            (event.charCode > 96 && event.charCode < 123);
    };
    doc.getElementById("pass_reg").onkeypress = function (event) {
        event = event || window.event;
        return (event.charCode > 47 && event.charCode < 58) ||
            (event.charCode > 64 && event.charCode < 91) ||
            (event.charCode > 96 && event.charCode < 123);
    }
}

function validation() {
    var doc = document;
    var log = doc.getElementById("log_reg").value;
    var phone = doc.getElementById("phone").value;
    var email = doc.getElementById("mail_reg").value;
    var pass = doc.getElementById("pass_reg").value;
    if (log.length > 4) {
        if (email.length > 0) {
            var at = email.indexOf("@");
            var dot = email.indexOf(".");
            if ((at < 0 || dot < 0) || (at > dot )) {
                alert('Email введен не верно \nПример:\n\n' +
                    'example@gmail.com');
                return;
            }
            if (pass.length > 4) {
                if (phone.length < 11) {
                    userReg(log, phone, email, pass);
                }
                else {
                    alert('Телефон должен быть не более 10 символов');
                }
            } else {
                alert('Пароль должен быть не менее 5 символов');
            }
        } else {
            alert('Введите Email');
        }
    } else {
        alert('Логин должен быть не менее 5 символов');
    }
}