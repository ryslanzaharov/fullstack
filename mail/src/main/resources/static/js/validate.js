function sendMessage() {
    var to = $("#to").val();
    var subject = $("#subject").val();
    var body = $("#body").val();
    var result = null;
    if (to == null) {
        result = result + ",enter to whom you send the message"
    }
    if (subject == null) {
        result = result + ",enter message subject"
    }
    if (body == null) {
        result = result + ",Enter your message"
    }
    if (result == null) {
        $.ajax({
            type: "POST",
            url: "send",
            contentType: "application/json",
            data: JSON.stringify({
                "to": to,
                "subject": subject,
                "body": body,
            }),
            success: function (data) {
                alert("Your message has been sent.");
                location.reload();
            },
            error: function (xhr, textStatus) {
                alert([xhr.status, textStatus]);
            }
        })
    } else {
        alert("Your message has not been sent." + result)
    }
}