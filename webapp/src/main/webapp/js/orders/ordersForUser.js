var doc = document;

function ordersForUser() {
    $.ajax({
        type: "GET",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "ordersForUser"},
        success: function (data) {
            var ordersList = data.users_orders;
            var allOrders = doc.getElementById("user_orders");
            for (var order in ordersList) {
                var div = doc.createElement("div");
                div.setAttribute("class", "user-orders");
                div.innerHTML =
                    "<div class='order-text'>" + ordersList[order].text + " </div>" +
                    "<div class='order-date'>" + ordersList[order].date + "</div>" +
                    "<input type='button' class='update-button' value='Обновить заявку' " +
                    "onclick='ordersForm(" + ordersList[order].id + "," +
                    "\"" + ordersList[order].text + "\"" + ")'/>" +
                    "<input type='button' class='delete-button' value='Удалить заявку' " +
                    "onclick='ordersDelete(" + ordersList[order].id + ")'/>";
                allOrders.parentNode.appendChild(div);
            }
        }
    });
}

function ordersForm(id, text) {
    doc.getElementById('hidden_update_orders').style.display = "block";
    doc.getElementById("id_orders").value = id;
    doc.getElementById("new_order").value = text;
}

function ordersDelete(id) {
    if (confirm("Вы точно хотите удалить заявку?") === true) {
        $.ajax({
            type: "POST",
            url: "/RepairComp",
            dataType: "json",
            data: {requestType: "ordersDelete", id_order: id},
            success: function (data) {
                switch (data.result) {
                    case  "complete":
                        location.reload(true);
                        break;
                    case  "error":
                        alert('Что-то пошло не так');
                        location.reload(true);
                        break;
                    default:
                        document.location.href = '../../authorization.html';
                }
            }
        });
    } else {
        location.reload(true);
    }
}