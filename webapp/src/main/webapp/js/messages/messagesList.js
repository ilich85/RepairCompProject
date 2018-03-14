function messagesList() {
    $.ajax({
        type: "GET",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "messagesList"},
        success: function (data) {
            var messagesList = data.messages;
            var allMessages = document.getElementById("all_messages");
            for (var message in messagesList) {
                var div = document.createElement("div");
                div.setAttribute("class", "messages");
                div.innerHTML =
                    "<div class='messages-text'>" + messagesList[message].mess_text + " </div>";
                allMessages.parentNode.appendChild(div);
            }
        }
    });
}