function commentsAdd() {
    var doc = document;
    var commentText = doc.getElementById("comment_text").value;
    $.ajax({
        type: "POST",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "commentsAdd", comment_text: commentText},
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