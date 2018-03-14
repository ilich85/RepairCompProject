function ordersListForAdmin() {
    $.ajax({
        type: "GET",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "ordersListForAdmin"},
        success: function (data) {
            var ordersList = data.orders;
            var allOrders = document.getElementById("orders_for_admin");
            for (var order in ordersList) {
                var div = document.createElement("div");
                div.setAttribute("class", "orders");
                div.innerHTML =
                    "<div class='orders-text'>" + ordersList[order].text + " </div>" +
                    "<div class='orders-date'>" + ordersList[order].date + "</div>" +
                    "<div  class='orders-user'>" + ordersList[order].user + "</div>" +
                    "<div  class='orders-phone'>" + ordersList[order].phone + "</div>" +
                    "<input type='button' class='delete-button' value='Удалить заявку' " +
                    "onclick='deletingAnOrderByAnAdmin(" + ordersList[order].id + ")'" +
                    "</div>";
                allOrders.parentNode.appendChild(div);
            }
        }
    });
}

function deletingAnOrderByAnAdmin(id) {
    if (confirm("Вы точно хотите удалить данную заявку?") === true) {
        $.ajax({
            type: "POST",
            url: "/RepairComp",
            dataType: "json",
            data: {requestType: "deletingAnOrderByAnAdmin", order_id: id},
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