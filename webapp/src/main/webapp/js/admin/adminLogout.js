function adminLogout() {
    $.ajax({
        type: "POST",
        url: "/RepairComp",
        dataType: "json",
        data: {requestType: "adminLogout"},
        success: function () {
            document.location.href = '../../admin/auth.html';
        }
    });
}