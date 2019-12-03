
function openAcc() {
    var username = $("#username").val();
    var password = $("#password").val();
    alert(username);

    var enabled = true;
    var result = null;
    var valid_log = /^[A-Z]+$/i;
    if (!username.match(valid_log)) {
        result = result + ", use english characters"
    }
    if (username == null || username.length < 3) {
        result = ",username at least 3 characters"
    }
    if (password == null || password.length < 5) {
        result = result + ",login at least 5 characters"
    }
    if (result == null) {
        $.ajax({
            type: "POST",
            url: "/registration",
            contentType: "application/json",
            data: JSON.stringify({
                "username": username, "password": password, "enabled": enabled
            }),
            success: function (response) {
                alert(response)
                location.reload();
            },
            error: function (xhr, textStatus) {
                alert([xhr.status, textStatus]);
            }
        })
    } else {
        alert("Your account is not opened " + result)
    }

}