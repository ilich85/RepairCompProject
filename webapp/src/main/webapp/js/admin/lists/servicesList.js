var doc = document;

function servicesListForAdmin() {
    $.ajax({
        type: "GET",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "servicesListForAdmin"},
        success: function (data) {
            var servicesList = data.services;
            var allServices = doc.getElementById("services_for_admin");
            for (var service in servicesList) {
                var div = doc.createElement("div");
                div.setAttribute("class", "services");
                div.innerHTML =
                    "<div class='service-descr'>" + servicesList[service].description + " </div>" +
                    "<div class='service-price'>" + servicesList[service].price + "</div>" +
                    "<input type='button' class='update-button' value='Обновить услугу'" +
                    "onclick='serviceForm(" + servicesList[service].id + "," +
                    "\"" + servicesList[service].description + "\"" + "," +
                    "\"" + servicesList[service].price + "\"" + ")'/>" +
                    "<input type='button' class='delete-button' value='Удалить услугу'" +
                    "onclick='servicesDelete(" + servicesList[service].id + ")'/>";
                allServices.parentNode.appendChild(div);
            }
        }
    });
}

function serviceForm(id, descr, price) {
    var updateForm = doc.getElementById('hidden_update_serv');
    updateForm.style.display = "block";
    doc.getElementById("id_service").value = id;
    doc.getElementById("new_descr").value = descr;
    doc.getElementById("new_price").value = price;
}