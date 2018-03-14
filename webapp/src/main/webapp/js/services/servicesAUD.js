var doc = document;

function servicesAdd() {
    var descr = doc.getElementById("description").value;
    var price = doc.getElementById("price").value;
    $.ajax({
        type: "POST",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "servicesAdd", description: descr, price: price},
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

function servicesUpdate() {
    var id = doc.getElementById("id_service").value;
    var newDescr = doc.getElementById("new_descr").value;
    var newPrice = doc.getElementById("new_price").value;
    $.ajax({
        type: "POST",
        url: "/RepairComp",
        dataType: "json",
        data: {
            requestType: "servicesUpdate", new_description: newDescr,
            new_price: newPrice, id_service: id
        },
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

function servicesDelete(id) {
    if (confirm("Вы точно хотите удалить данную услугу?") === true) {
        $.ajax({
            type: "POST",
            url: "/RepairComp",
            dataType: "json",
            data: {requestType: "servicesDelete", id_service: id},
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

