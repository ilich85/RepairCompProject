function commentsListForAdmin() {
    $.ajax({
        type: "POST",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "commentsListForAdmin"},
        success: function (data) {
            var commentsList = data.comments;
            var listComments = document.getElementById("comments_for_admin");
            for (var comment in commentsList) {
                var div = document.createElement("div");
                div.setAttribute("class", "comments");
                div.innerHTML =
                    "<div class='comments-text'>" + commentsList[comment].text + " </div>" +
                    "<div class='comments-date'>" + commentsList[comment].date + "</div>" +
                    "<div  class='comments-user'>" + commentsList[comment].user + "</div>" +
                    "<input type='button' class='delete-button' value='Удалить отзыв'" +
                    "onclick='deletingAnCommentByAnAdmin(" + commentsList[comment].id + ")'/>";
                listComments.parentNode.appendChild(div);
            }
        }
    });
}
function deletingAnCommentByAnAdmin(id) {
    if (confirm("Вы точно хотите удалить данный отзыв?") === true) {
        $.ajax({
            type: "POST",
            url: "/RepairComp",
            dataType: "json",
            data: {requestType: "deletingAnCommentByAnAdmin", comment_id: id},
            success: function (data) {
                switch (data.result) {
                    case "complete":
                        location.reload(true);
                        break;
                    default:
                        document.location.href = '../../authorization.html';
                        break;
                }
            }
        });
    }
    else {
        location.reload(true);
    }
}