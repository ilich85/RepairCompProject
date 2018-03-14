function ordersAdd() {
    var doc = document;
    var orderText = doc.getElementById("order_text").value;
    $.ajax({
        type: "POST",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "ordersAdd", order_text: orderText},
        success: function (data) {
            switch (data.result) {
                case "complete":
                    alert('Менеджер свяжется с вами в ближайшее время.');
                    location.reload(true);
                    break;
                default:
                    doc.location.href = '../../authorization.html';
                    break;
            }
        }
    });
}