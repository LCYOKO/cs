var uinfo = {
    id:  'KF' + uid,
    username: uname,
    avatar: avatar,
    group: group
};


$(function () {
    // 获取服务用户列表
    $.getJSON('/service/index/getUserList', function(res){
        if(1 == res.code && res.data.length > 0){
            $.each(res.data, function(k, v){
                addUser(v);
            });

            var id = $(".layui-unselect").find('li').eq(0).data('id');
            var name = $(".layui-unselect").find('li').eq(0).data('name');
            var avatar = $(".layui-unselect").find('li').eq(0).data('avatar');
            var ip = $(".layui-unselect").find('li').eq(0).data('ip');

            // 默认设置第一个用户为当前对话的用户
            $("#active-user").attr('data-id', id).attr('data-name', name).attr('data-avatar', avatar).attr('data-ip', ip);

            $(".layui-unselect").find('li').eq(0).addClass('active').find('span:eq(1)').removeClass('layui-badge').text('');
            $("#f-user").val(name);
            $("#f-ip").val(ip);

            $.getJSON('/service/index/getCity', {ip: ip}, function(res){
                $("#f-area").val(res.data);
            });

            // 拉取和这个人的聊天记录
            $("#u-" + id).show();
            getChatLog(id, 1);
        }
    });

    // 监听快捷键发送
    document.getElementById('msg-area').addEventListener('keydown', function (e) {
        if (e.keyCode != 13) return;
        e.preventDefault();  // 取消事件的默认动作
        sendMessage();
    });

    // 点击表情
    var index;
    $("#face").click(function (e) {
        e.stopPropagation();
        layui.use(['layer'], function () {
            var layer = layui.layer;

            var isShow = $(".layui-laykefu-face").css('display');
            if ('block' == isShow) {
                layer.close(index);
                return;
            }
            var height = $(".chat-box").height() - 110;
            layer.ready(function () {
                index = layer.open({
                    type: 1,
                    offset: [height + 'px', $(".layui-side").width() + 'px'],
                    shade: false,
                    title: false,
                    closeBtn: 0,
                    area: '395px',
                    content: showFaces()
                });
            });
        });
    });

    $(document).click(function (e) {
        layui.use(['layer'], function () {
            var layer = layui.layer;
            if (isShow) {
                layer.close(index);
                return false;
            }
        });
    });

    // 发送消息
    $("#send").click(function () {
        sendMessage();
    });

    // hover用户
    $(".layui-unselect li").hover(function () {
        $(this).find('i').show();
    }, function () {
        $(this).find('i').hide();
    });



    //关闭当前会话
    $("#closeSession").click(function(){
        var layerIndex;
        console.log(1111)
        layerIndex = layer.confirm('确定关闭会话' , {
            title: '关闭提示',
            closeBtn: 0,
            icon: 3,
            btn: ['确定', '取消'] // 按钮
        }, function(){
            // layer.close(layerIndex);
            // layerIndex = layer.open({
            //     title: '',
            //     type: 1,
            //     area: ['30%', '40%'],
            //     content: $("#change-box")
            // });
        });

        });



    // 关闭用户
    $('.close').click(function () {
        var uid = $(this).parent().data('id');
        $(this).parent().remove(); // 清除左侧的用户列表
        $('#u-' + uid).remove(); // 清除右侧的聊天详情
    });

    // 检测滚动，异步加载更多聊天数据
    $(".chat-box").scroll(function () {
        var top = $(".chat-box").scrollTop();
    });

    // 会员转接
    $("#scroll-link").click(function(){

        var id = $("#active-user").attr('data-id');
        var name = $("#active-user").attr('data-name');
        var avatar = $("#active-user").attr('data-avatar');
        var ip = $("#active-user").attr('data-ip');

        if(id == '' || name == ''){
            layer.msg("请选择要转接的会员");
        }

        // 二次确认
        var layerIndex = null;
        layerIndex = layer.confirm('确定转接 ' + name + ' ？', {
            title: '转接提示',
            closeBtn: 0,
            icon: 3,
            btn: ['确定', '取消'] // 按钮
        }, function(){
            layer.close(layerIndex);
            layerIndex = layer.open({
                title: '',
                type: 1,
                area: ['30%', '40%'],
                content: $("#change-box")
            });


        }, function(){

        });
    });


});



