var doc = document;

function commentsForUser() {
    $.ajax({
        type: "POST",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "commentsForUser"},
        success: function (data) {
            var commentsList = data.users_comments;
            var userComments = doc.getElementById("user_comments");
            for (var comment in commentsList) {
                var div = doc.createElement("div");
                div.setAttribute("class", "user-comments");
                div.innerHTML =
                    "<div class='comments-text'>" + commentsList[comment].text + " </div>" +
                    "<div class='comments-date'>" + commentsList[comment].date + "</div>" +
                    "<input type='button' class='update-button'  value='Обновить отзыв' " +
                    "onclick='commentsForm(" + commentsList[comment].id + "," +
                    "\"" + commentsList[comment].text + "\"" + ")'/>" +
                    "<input type='button' class='delete-button' value='Удалить отзыв' " +
                    "onclick='commentsDelete(" + commentsList[comment].id + ")'/>";
                userComments.parentNode.appendChild(div);
            }
        }
    });
}

function commentsForm(id, text) {
    var updateForm = doc.getElementById('hidden_update_comments');
    updateForm.style.display = "block";
    doc.getElementById("id_comment").value = id;
    doc.getElementById("new_text").value = text;
}