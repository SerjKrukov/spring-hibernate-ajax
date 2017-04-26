$(document).ready(function () {

    $("#ajax-form").submit(function (event) {


        event.preventDefault();

        doAjaxPost();

    });

});
function doAjaxPost() {
    var userData = {}
    userData["name"] = $("#namejs").val();
    userData["age"] = $("#agejs").val();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/addWithAjax",
        data: JSON.stringify(userData),
        dataType: 'json',
        cahce: false,
        timeout: 600000,
        success: function(data) {
            var json = "<h4>Ajax Response</h4><pre>"
            + JSON.stringify(data, null, 4) + "</pre>";
            $('#lastadded').html(json);
        },
        error: function (e) {
             var json = "<h4>Ajax Response</h4><pre>"
             + e.responseText + "</pre>";
             $('#lastadded').html(json);
        }
    });
}