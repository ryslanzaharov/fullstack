
$(document).ready(function() {

    $('#1').click(function() {
        messages(1);
    });
    $('#2').click(function() {
        messages(2);
    });
    $('#3').click(function() {
        messages(3);
    });

});

function messages(id) {
    $.ajax({
        type : 'GET',
        url : '/chat/' + id,
        dataType : 'json',
        contentType : 'application/json',
        success : function(result) {
            var s = '';
            s += '<br/>  <div><label>Send message : <input type="text" id="message"/> </label>\n' +
                '    <button type="button" onclick="sendMessages('+ id +')">Send</button>\n' +
                '</div>'
            for (var i = 0; i < result.length; i++) {
                s += '<br/> ' + result[i].user.username + ' : ' + result[i].message;
                s += '<br/>======================';
            }
            $('#result4').html(s);
        }
    });
}

function sendMessages(id) {
    var message = $("#message").val();
    $.ajax({
        type : 'POST',
        url : '/chat/' + id + '/' + message,
        dataType : 'json',
        contentType : 'application/json',
        success : function(result) {
            var s = '';
            s += '<br/>  <div><label>Send message : <input type="text" id="message" /> </label>\n' +
                '    <button type="button"  onclick="messages('+ id +')">Send</button>\n' +
                '</div>'
            for (var i = 0; i < result.length; i++) {
                s += '<br/> ' + result[i].user.username + ' : ' + result[i].message;
                s += '<br/>======================';
            }
            $('#result4').html(s);
        }
    });
}

