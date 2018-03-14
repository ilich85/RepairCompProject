function adminAuth() {
    var doc = document;
    var adminName = doc.getElementById("admin_name").value;
    var pass = doc.getElementById("password").value;
    $.ajax({
        type: "GET",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "adminAuth", admin_name: adminName, password: pass},
        success: function (data) {
            switch (data.result) {
                case "complete":
                    doc.location.href = '../../admin/main.html';
                    break;
                case "super":
                    doc.location.href = '../../admin/superadmin.html';
                    break;
                case "wrong_pass":
                    alert('Неправильный пароль');
                    location.reload(true);
                    break;
                default:
                    alert("Ты кто такой?\nДавай до свиданья!!!");
                    doc.location.href = '../../authorization.html';
                    break;
            }
        }
    });
}