
function init(uid) {
    console.log(123+""+uid);
    var socket = new SockJS("http://localhost:8082/ws");

    // 获取 STOMP 子协议的客户端对象
     stompClient = Stomp.over(socket);
    // 向服务器发起websocket连接并发送CONNECT帧
    stompClient.connect(
        {
            name: 'test' // 携带客户端信息
        },
        function connectCallback(frame) {
            // 连接成功时（服务器响应 CONNECTED 帧）的回调方法
            console.log(frame);
        },
        function errorCallBack(error) {
            // 连接失败时（服务器响应 ERROR 帧）的回调方法

        }
    );
}

function subscribe3() {
    stompClient.subscribe('/user/queue/message', function (response) {
        var returnData = JSON.parse(response.body);
        console.log(123);
    });
}

function sendMessage(SessionId,msg) {
    console.log('发送数据')

    stompClient.send("/app/hello",{},JSON.stringify({'msg':msg}));
}