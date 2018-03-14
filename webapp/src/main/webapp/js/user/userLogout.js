function userLogout() {
    $.ajax({
        type: "POST",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "userLogout"},
        success: function () {
            document.location.href = '../../authorization.html';
        }
    });
}