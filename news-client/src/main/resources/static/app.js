var stompClient = null;

function connect() {
    var socket = new SockJS('/news-websocket');
    stompClient = Stomp.over(socket);

    stompClient.connect({},
        function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/news', function (news) {
                var newsBody = JSON.parse(news.body);
                var card = '<div class="card">' +
                               '<div class="card-content">' +
                                   '<span class="left blue lighten-4">'+newsBody.category.toUpperCase()+'</span>' +
                                   '<span class="right grey-text lighten-4">'+moment(newsBody.datetime).format("DD-MMM-YYYY HH:mm:ss")+'</span>' +
                                   '<div class="clearfix"></div>' +
                                   '<h5>'+newsBody.title+'</h5>' +
                                   '<div class="divider"></div>' +
                                   '<p>'+newsBody.text+'</p>' +
                               '</div>' +
                           '</div>';
                $('#newsList').prepend(card);
            });
        },
        function() {
            console.log('Unable to connect to Websocket!');
            $('#websocketSwitch').prop('checked', false);
        }
     );
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

$(function () {
    $('#websocketSwitch').click(function() {
        if ($(this).prop('checked')) {
            connect();
        } else {
            disconnect();
        }
    });
});

connect();