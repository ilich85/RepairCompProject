function commentsDelete(id) {
    if (confirm("Вы точно хотите удалить отзыв?") === true) {
        $.ajax({
            type: "POST",
            url: "/RepairComp",
            dataType: "json",
            data: {requestType: "commentsDelete", id_comment: id},
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