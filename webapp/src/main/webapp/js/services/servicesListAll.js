function servicesListAll() {
    $.ajax({
        type: "GET",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "servicesListAll"},
        success: function (data) {
            var servicesList = data.services;
            var allServices = document.getElementById("all_services");
            for (var service in servicesList) {
                var div = document.createElement("div");
                div.setAttribute("class", "services");
                div.innerHTML =
                    "<div class='service-descr'>" + servicesList[service].description + " </div>" +
                    "<div class='service-price'>" + servicesList[service].price + "</div>";
                allServices.parentNode.appendChild(div);
            }
        }
    });
}