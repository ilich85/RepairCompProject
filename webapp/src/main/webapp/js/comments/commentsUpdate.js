function commentsUpdate() {
    var id = document.getElementById("id_comment").value;
    var newText = document.getElementById("new_text").value;
    $.ajax({
        type: "POST",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "commentsUpdate", new_text: newText, id_comment: id},
        success: function (data) {
            switch (data.result) {
                case "complete":
                    location.reload(true);
                    break;
                case "wrong":
                    alert('Нет доступа');
                    location.reload(true);
                    break;
                default:
                    document.location.href = '../../authorization.html';
                    break;
            }
        }
    });
}