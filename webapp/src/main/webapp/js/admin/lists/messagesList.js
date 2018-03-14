var doc = document;

function messagesListForAdmin() {
    $.ajax({
        type: "GET",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "messagesListForAdmin"},
        success: function (data) {
            var messagesList = data.messages;
            var allMessages = doc.getElementById("messages_for_admin");
            for (var mess in messagesList) {
                var div = doc.createElement("div");
                div.setAttribute("class", "messages");
                div.innerHTML =
                    "<div class='mess-text'>" + messagesList[mess].mess_text + " </div>" +
                    "<input type='button' class='update-button' value='Обновить новость'" +
                    "onclick='messagesForm(" + messagesList[mess].id + "," +
                    "\"" + messagesList[mess].mess_text + "\"" + ")'/>" +
                    "<input type='button' class='delete-button' value='Удалить новость'" +
                    "onclick='messagesDelete(" + messagesList[mess].id + ")'/>";
                allMessages.parentNode.appendChild(div);
            }
        }
    });
}

function messagesForm(id, oldText) {
    var messForm = doc.getElementById("hidden_update_message");
    messForm.style.display = "block";
    doc.getElementById("id_message").value = id;
    doc.getElementById("new_message").value = oldText;
}