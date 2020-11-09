
function init(type,username,uid) {



  //var protocol = location.protocol === 'https:' ? 'wss://' : 'ws://';
  socketClient = new WebSocket("ws://localhost:8082/webSocket/"+type+"?username="+username+"&uid="+uid);//创建Socket实例// 打开Socket
socketClient.onopen = function (res) {
    layui.use(['layer'], function () {
        var layer = layui.layer;
        layer.ready(function () {
            layer.msg('链接成功', {time: 1000});
        });
    });

    //每30秒ping服务器
    setInterval(function(){
        // socket.send('{"type":"ping"}');
        socket.send(JSON.stringify({
            type: 'ping',
            l_user_id: 1
        }));
    },30000);
};
//
// 监听消息
socketClient.onmessage = function (res) {
    var data = eval("(" + res.data + ")");
         console.log(data);
    switch (data['type']) {
        // 服务端ping客户端
        case 101:
            socket.send('{"type":"ping"}');
            break;
        // 添加用户
        case 2:
            addUser(data.data.user_info);
            break;
        // 移除访客到主面板
        case 'delUser':
            delUser(data.data);
            break;
        // 监测聊天数据
        case 1:
            showUserMessage(data);
            break;
        case 'close':
            loginOut();
            break;
    }
};

 // 监听失败
    socketClient.onerror = function(err){
                console.log(err)
        layer.alert('连接失败,请联系管理员', {icon: 2, title: '错误提示'});
    };
    var socket = new SockJS("http://localhost:8082/ws");

    // // 获取 STOMP 子协议的客户端对象
    //  stompClient = Stomp.over(socket);
    // // 向服务器发起websocket连接并发送CONNECT帧
    // stompClient.connect(
    //     {
    //         name: 'test' // 携带客户端信息
    //     },
    //     function connectCallback(frame) {
    //         // 连接成功时（服务器响应 CONNECTED 帧）的回调方法
    //         console.log(frame);
    //     },
    //     function errorCallBack(error) {
    //         // 连接失败时（服务器响应 ERROR 帧）的回调方法
    //
    //     }
    // );
}

function createMessage(msg,uid,uname) {
    var data={
        toId: localStorage.getItem("fromId"),
        toName:localStorage.getItem("fromName"),
        fromId:uid,
        fromName:uname,
        msg:msg,
        type:1
            };
    return JSON.stringify(data);
}




