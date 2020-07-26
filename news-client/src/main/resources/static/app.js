let stompClient = null

function connect() {
    const socket = new SockJS('/news-websocket')
    stompClient = Stomp.over(socket)

    const $webSocketSwitch = $('#webSocketSwitch')

    stompClient.connect({},
        function (frame) {
            console.log('Connected: ' + frame)
            stompClient.subscribe('/topic/news', function (news) {
                const newsBody = JSON.parse(news.body)
                const newsItem = '<div class="item">' +
                                   '<div class="image">' +
                                     '<img src="img/'+newsBody.category+'.jpg" style="width:150px; height:120px">' +
                                   '</div>' +
                                   '<div class="content">' +
                                     '<div class="meta" style="float:right">' +
                                       '<span>'+moment(newsBody.datetime).format("DD-MMM-YYYY HH:mm:ss")+'</span>' +
                                     '</div>' +
                                     '<div class="ui medium blue header">'+newsBody.category.toUpperCase()+'</div>' +
                                     '<div class="ui divider"></div>' +
                                     '<div class="ui large header">'+newsBody.title+'</div>' +
                                     '<div class="description">' +
                                       '<p>'+newsBody.text+'</p>' +
                                     '</div>' +
                                   '</div>' +
                                 '</div>'

                $('#newsList').prepend(newsItem)
            })
            $webSocketSwitch.removeClass('disabled')
        },
        function() {
            console.log('Unable to connect to Websocket!')
            $webSocketSwitch.addClass('disabled')
        }
     )
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect()
    }
    console.log("Disconnected")
}

$(function () {
    $('#webSocketSwitch').checkbox({
        onChecked: function() { connect() },
        onUnchecked: function() { disconnect() }
    })
})

connect()