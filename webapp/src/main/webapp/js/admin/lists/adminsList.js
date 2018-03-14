function adminsList() {
    $.ajax({
        type: "GET",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "adminsList"},
        success: function (data) {
            var adminsList = data.admins;
            var allAdmins = document.getElementById("admins_to_update");
            for (var admin in adminsList) {
                var div = document.createElement("div");
                div.setAttribute("class", "admin");
                div.innerHTML =
                    "<div >" + "Логин: " + adminsList[admin].admin_name + " </div>" +
                    "<div >" + "Email: " + adminsList[admin].email + "</div>" +
                    "<input type='button' class='update-button' value='Обновить данные'" +
                    "onclick='updateForm(" + adminsList[admin].id + "," +
                    "\"" + adminsList[admin].admin_name + "\"" + "," +
                    "\"" + adminsList[admin].email + "\"" + "," +
                    "\"" + adminsList[admin].password + "\"" + ")'/>" +
                    "<input type='button' class='delete-button' value='Удалить админа'" +
                    "onclick='adminDelete(" + adminsList[admin].id + ")'/>";
                allAdmins.parentNode.appendChild(div);
            }
        }
    });
}