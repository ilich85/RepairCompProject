var doc = document;

function userInfo() {
    $.ajax({
        type: "GET",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "userInfo"},
        success: function (data) {
            var userInfo = data.user_info;
            var info = doc.getElementById("info_user");
            var div = doc.createElement("div");
            div.setAttribute("class", "user-info");
            div.innerHTML =
                "<div>" + "Логин:" + userInfo.username + "</div>" + "<br>" +
                "<div>" + "Email:" + userInfo.email + "</div>" + "<br>" +
                "<div>" + "Телефон:" + userInfo.phone + "</div>" + "<br>" +
                "<div>" + "Имя:" + userInfo.first_name + "</div>" + "<br>" +
                "<div>" + "Фамилия:" + userInfo.last_name + "</div>" + "<br>" +
                "<input type='button' class='update-button' value='Обновить данные'" +
                "onclick='userInfoForm(" + userInfo.user_id + "," +
                "\"" + userInfo.phone + "\"" + "," +
                "\"" + userInfo.first_name + "\"" + "," +
                "\"" + userInfo.last_name + "\"" + "," + ")'/>" + "<br/>" +
                "<input type='button' class='pass-button' " +
                "onclick='updatePassForm()' value='Изменить пароль'/>" +
                "<input type='button' class='delete-button' value='Удалить аккаунт'" +
                "onclick='userDeleteForm()'/>";
            info.parentNode.appendChild(div);
        }
    });
}

function userInfoForm(id, phone, fname, lname) {
    var updateForm = doc.getElementById('hidden_update_info');
    updateForm.style.display = "block";
    doc.getElementById("id_user").value = id;
    doc.getElementById("phone").value = phone;
    doc.getElementById("first_name").value = fname;
    doc.getElementById("last_name").value = lname;
}

function updatePassForm() {
    var updateForm = doc.getElementById('hidden_update_pass');
    updateForm.style.display = "block";
}

function userDeleteForm() {
    var deleteForm = doc.getElementById('hidden_delete_user');
    deleteForm.style.display = "block";
}