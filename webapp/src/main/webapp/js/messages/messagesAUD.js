var doc = document;

function messagesAdd() {
    var messageText = doc.getElementById("message_text").value;
    $.ajax({
        type: "POST",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "messagesAdd", message_text: messageText},
        success: function (data) {
            switch (data.result) {
                case "complete":
                    location.reload(true);
                    break;
                default:
                    doc.location.href = '../../authorization.html';
                    break;
            }

        }
    });
}

function messagesUpdate() {
    var idMess = doc.getElementById("id_message").value;
    var newMess = doc.getElementById("new_message").value;
    $.ajax({
        type: "POST",
        url: "/RepairComp",
        dataType: "json",
        data: {
            requestType: "messagesUpdate",
            new_message: newMess, id_message: idMess
        },
        success: function (data) {
            switch (data.result) {
                case "complete":
                    location.reload(true);
                    break;
                default:
                    doc.location.href = '../../authorization.html';
                    break;
            }
        }
    });
}

function messagesDelete(id) {
    if (confirm("Вы точно хотите удалить данную новость?") === true) {
        $.ajax({
            type: "POST",
            url: "/RepairComp",
            dataType: "json",
            data: {requestType: "messagesDelete", id_message: id},
            success: function (data) {
                switch (data.result) {
                    case "complete":
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