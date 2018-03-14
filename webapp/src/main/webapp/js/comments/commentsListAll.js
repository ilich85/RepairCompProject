function commentsListAll() {
    $.ajax({
        type: "GET",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "commentsListAll"},
        success: function (data) {
            var commentsList = data.comments;
            var allComments = document.getElementById("all_comments");
            for (var comment in commentsList) {
                var div = document.createElement("div");
                div.setAttribute("class", "comments");
                div.innerHTML =
                    "<div class='comments-text'>" + commentsList[comment].text + " </div>" +
                    "<div class='comments-date'>" + commentsList[comment].date + "</div>" +
                    "<div  class='comments-user'>" + commentsList[comment].user + "</div>";
                allComments.parentNode.appendChild(div);
            }
        }
    });
}