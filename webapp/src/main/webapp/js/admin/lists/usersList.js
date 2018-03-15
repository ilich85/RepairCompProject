function usersList() {
    $.ajax({
        type: "GET",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "usersList"},
        success: function (data) {
            var usersList = data.users;
            var allUsers = document.getElementById("users_list");
            for (var user in usersList) {
                var div = document.createElement("div");
                div.setAttribute("class", "users");
                div.innerHTML =
                    "<div>" + "Логин: " + usersList[user].username + " </div>" +
                    "<div>" + "Email: " + usersList[user].email + "</div>" +
                    "<div>" + "Телефон: " + usersList[user].phone + "</div>" +
                    "<div>" + "Имя: " + usersList[user].first_name + "</div>" +
                    "<div>" + "Фамилия: " + usersList[user].last_name + "</div>" +
                    "<input type='button' class='delete-button' value='Удалить аккаунт'" +
                    "onclick='userRemovalByAdmin(" + usersList[user].id + ")'/>";
                allUsers.parentNode.appendChild(div);
            }
        }
    });
}
function userRemovalByAdmin(id) {
    if (confirm("Вы точно хотите удалить данного пользователя?") === true) {
        $.ajax({
            type: "POST",
            url: "/RepairComp",
            dataType: "json",
            data: {requestType: "userRemovalByAdmin", id_user: id},
            success: function (data) {
                switch (data.result) {
                    case "complete":
                        location.reload(true);
                        break;
                    default:
                        doc.location.href = '../../../authorization.html';
                        break;
                }
                if (data.result == "complete") {
                    alert('Пользователь удален');
                    location.reload(true);
                }
                else if (data.result == "wrong") {
                    alert('Нет доступа');
                    document.location.href = '../../../user/main.html';
                }
                else if (data.result == "error") {
                    alert('Что-то пошло не так');
                    location.reload(true);
                }
            }
        });
    }
}