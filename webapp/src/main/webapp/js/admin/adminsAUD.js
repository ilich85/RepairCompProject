var doc = document;
function adminReg() {
    var newAdminName = doc.getElementById("new_admin_name").value;
    var email = doc.getElementById("new_email").value;
    var pass = doc.getElementById("new_password").value;
    $.ajax({
        type: "POST",
        url: "/RepairComp",
        dataType: "json",
        data: {
            requestType: "adminReg", new_admin_name: newAdminName,
            new_email: email, new_password: pass,
        }, success: function (data) {
            switch (data.result) {
                case "complete":
                    alert('Менеджер успешно зарегистрирован');
                    location.reload(true);
                    break;
                case "error":
                    alert('Нет доступа!');
                    location.reload(true);
                    break;
                default:
                    doc.location.href = '../../authorization.html';
                    break;
            }
        }
    });
}

function adminUpdate() {

    var idAdm = doc.getElementById("id_admin").value;
    var adminName = doc.getElementById("admin_name").value;
    var mailAdm = doc.getElementById("email").value;
    var passAdm = doc.getElementById("password").value;
    $.ajax({
        type: "POST",
        url: "/RepairComp",
        dataType: "json",
        data: {
            requestType: "adminUpdate", id: idAdm, admin_name: adminName,
            email: mailAdm, password: passAdm,
        },
        success: function (data) {
            switch (data.result) {
                case "complete":
                    location.reload(true);
                    break;
                case "error":
                    alert('Нет доступа!');
                    location.reload(true);
                    break;
                default:
                    doc.location.href = '../../authorization.html';
                    break;
            }
        }
    });
}

function updateForm(id, login, email, pass) {
    var updateForm = doc.getElementById('hidden_update');
    updateForm.style.display = "block";
    doc.getElementById("id_admin").value = id;
    doc.getElementById("admin_name").value = login;
    doc.getElementById("email").value = email;
    doc.getElementById("password").value = pass;
}

function adminDelete(id) {
    if (confirm("Вы точно хотите удалить данного админа?") === true) {
        $.ajax({
            type: "POST",
            url: "/RepairComp",
            dataType: "json",
            data: {
                requestType: "adminDelete", id: id
            },
            success: function (data) {
                switch (data.result) {
                    case "complete":
                        location.reload(true);
                        break;
                    case "forbidden":
                        alert('Нельзя удалить суперадмина');
                        location.reload(true);
                        break;
                    case "error":
                        alert('Нет доступа!');
                        location.reload(true);
                        break;
                    default:
                        doc.location.href = '../../authorization.html';
                        break;
                }
            }
        });
    }
    else {
        location.reload(true);
    }
}