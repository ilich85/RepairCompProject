function ordersUpdate() {
    var id = document.getElementById("id_orders").value;
    var newText = document.getElementById("new_order").value;
    $.ajax({
        type: "POST",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "ordersUpdate", new_text: newText, id_order: id},
        success: function (data) {
            switch (data.result) {
                case "complete":
                    location.reload(true);
                    break;
                case "wrong":
                    alert('Что-то пошло не так');
                    location.reload(true);
                    break;
                default:
                    doc.location.href = '../../authorization.html';
                    break;
            }
        }
    });
}