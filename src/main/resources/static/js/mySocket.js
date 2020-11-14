
function init(type,username,uid) {



  //var protocol = location.protocol === 'https:' ? 'wss://' : 'ws://';
  socketClient = new WebSocket("ws://10.224.206.5:8082/webSocket/"+type+"?username="+username+"&uid="+uid);//创建Socket实例// 打开Socket
socketClient.onopen = function (res) {
    layui.use(['layer'], function () {
        var layer = layui.layer;
        layer.ready(function () {
            layer.msg('链接成功', {time: 1000});
        });
    });

    //每30秒ping服务器
    // setInterval(function(){
    //     // socket.send('{"type":"ping"}');
    //     socket.send(JSON.stringify({
    //         type: 'ping',
    //         l_user_id: 1
    //     }));
    // },30000);
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
            addUser(data);
            break;
        // 移除访客到主面板
        case 'delUser':
            delUser(data);
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

}

function createMessage(msg,uid,uname) {
    var data={
        toUid: localStorage.getItem("fromId"),
        toUsername:localStorage.getItem("fromName"),
        fromUid:uid,
        fromUsername:uname,
        msg:msg,
        type:1
            };
    return JSON.stringify(data);
}




