// 转义聊天内容中的特殊字符
function replaceContent(content) {
    // 支持的html标签
    var html = function (end) {
        return new RegExp('\\n*\\[' + (end || '') + '(pre|div|span|p|table|thead|th|tbody|tr|td|ul|li|ol|li|dl|dt|dd|h2|h3|h4|h5)([\\s\\S]*?)\\]\\n*', 'g');
    };

    content = content.replace(/&(?!#?[a-zA-Z0-9]+;)/g, '&amp;')
        .replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/'/g, '&#39;').replace(/"/g, '&quot;') // XSS
        .replace(/@(\S+)(\s+?|$)/g, '@<a href="javascript:;">$1</a>$2') // 转义@

        .replace(/face\[([^\s\[\]]+?)\]/g, function (face) {  // 转义表情
            var alt = face.replace(/^face/g, '');
            return '<img alt="' + alt + '" title="' + alt + '" src="' + faces[alt] + '">';
        })
        .replace(/img\[([^\s]+?)\]/g, function (img) {  // 转义图片
            return '<img class="layui-laykefu-photos" src="' + img.replace(/(^img\[)|(\]$)/g, '') + '" width="100px" height="100px">';
        })
        .replace(/file\([\s\S]+?\)\[[\s\S]*?\]/g, function (str) { // 转义文件
            var href = (str.match(/file\(([\s\S]+?)\)\[/) || [])[1];
            var text = (str.match(/\)\[([\s\S]*?)\]/) || [])[1];
            if (!href) return str;
            return '<a class="layui-laykefu-file" href="' + href + '" download target="_blank"><i class="layui-icon">&#xe61e;</i><cite>' + (text || href) + '</cite></a>';
        })
        .replace(/a\([\s\S]+?\)\[[\s\S]*?\]/g, function (str) { // 转义链接
            var href = (str.match(/a\(([\s\S]+?)\)\[/) || [])[1];
            var text = (str.match(/\)\[([\s\S]*?)\]/) || [])[1];
            if (!href) return str;
            return '<a href="' + href + '" target="_blank">' + (text || href) + '</a>';
        }).replace(html(), '\<$1 $2\>').replace(html('/'), '\</$1\>') // 转移HTML代码
        .replace(/\n/g, '<br>') // 转义换行

    return content;
};

// 表情替换
var faces = function () {
    var alt = getFacesIcon(), arr = {};
    layui.each(alt, function (index, item) {
        arr[item] = '/static/service/js/layui/images/face/' + index + '.gif';
    });
    return arr;
}();

// 表情对应数组
function getFacesIcon() {
    return ["[微笑]", "[嘻嘻]", "[哈哈]", "[可爱]", "[可怜]", "[挖鼻]", "[吃惊]", "[害羞]", "[挤眼]", "[闭嘴]", "[鄙视]",
        "[爱你]", "[泪]", "[偷笑]", "[亲亲]", "[生病]", "[太开心]", "[白眼]", "[右哼哼]", "[左哼哼]", "[嘘]", "[衰]",
        "[委屈]", "[吐]", "[哈欠]", "[抱抱]", "[怒]", "[疑问]", "[馋嘴]", "[拜拜]", "[思考]", "[汗]", "[困]", "[睡]",
        "[钱]", "[失望]", "[酷]", "[色]", "[哼]", "[鼓掌]", "[晕]", "[悲伤]", "[抓狂]", "[黑线]", "[阴险]", "[怒骂]",
        "[互粉]", "[心]", "[伤心]", "[猪头]", "[熊猫]", "[兔子]", "[ok]", "[耶]", "[good]", "[NO]", "[赞]", "[来]",
        "[弱]", "[草泥马]", "[神马]", "[囧]", "[浮云]", "[给力]", "[围观]", "[威武]", "[奥特曼]", "[礼物]", "[钟]",
        "[话筒]", "[蜡烛]", "[蛋糕]"]
}


var isShow = false;

layui.use(['element', 'form','table'], function () {
    var element = layui.element;
    var form = layui.form;
    var table=layui.table;
    var mockData=[
        {"content":"顾客您好，有什么可以帮您的吗"},
        {"content":"无所谓的！！"},
        {"content":"火速结束聊天"}
    ]
    table.render({
        elem: '#QATable'
        ,height: 312
        ,page: true //开启分页
        ,data:mockData
        ,cols: [[ //表头
            {field: 'content', title: '内容', width:"80%"}
            ,{field: 'operate', title: '操作', width:"20%",
                templet:function () {
                    return '<a href="javascript:;" onclick="sendWord(this)"  style="color:#009688">应用</a>'
                }
            }

        ]]
    })
});

// 图片 文件上传, 暂时未实现
layui.use(['upload', 'layer'], function () {
    var upload = layui.upload;
    var layer = layui.layer;

    // 执行实例
    var uploadInstImg = upload.render({
        elem: '#image' // 绑定元素
        , accept: 'images'
        , exts: 'jpg|jpeg|png|gif'
        , url: '/service/upload/uploadImg' // 上传接口
        , done: function (res) {

            sendMessage('img[' + res.data.src + ']');
            showBigPic();
        }
        , error: function () {
            // 请求异常回调
        }
    });

    var uploadInstFile = upload.render({
        elem: '#file' // 绑定元素
        , accept: 'file'
        , exts: 'zip|rar'
        , url: '/service/upload/uploadFile' // 上传接口
        , done: function (res) {
            sendMessage('file(' + res.data.src + ')[' + res.msg + ']');
        }
        , error: function () {
            // 请求异常回调
        }
    });
});

// 展示表情数据
function showFaces() {
    isShow = true;
    var alt = getFacesIcon();
    var _html = '<div class="layui-laykefu-face"><ul class="layui-clear laykefu-face-list">';
    layui.each(alt, function (index, item) {
        _html += '<li title="' + item + '" onclick="checkFace(this)"><img src="../../static/layui/images/face/' + index + '.gif" /></li>';
    });
    _html += '</ul></div>';

    return _html;
}

// 选择表情
function checkFace(obj) {
    var word = $(".msg-area").val() + ' face' + $(obj).attr('title') + ' ';
    $(".msg-area").val(word).focus();
}

// 发送消息
function sendMessage(sendMsg) {
    var msg = (typeof(sendMsg) == 'undefined') ? $("#msg-area").val() : sendMsg;
    if ('' == msg) {
        layui.use(['layer'], function () {
            var layer = layui.layer;
            return layer.msg('请输入回复内容', {time: 1000});
        });
        return false;
    }

    var uid = $("#active-user").attr('data-id');
    var uname = $("#active-user").attr('data-name');
    var sendData=createMessage(msg,uid,uname);
   socketClient.send(sendData);
    var word = msgFactory(msg, 'mine', uinfo);

    $("#" + uid).append(word);
    $(".msg-area").val('');
    // 滚动条自动定位到最底端
    wordBottom();

}
function f() {
    var word = msgFactory("123", "", {name:"wule"});
    var uid = $("#active-user").attr('data-id');
    var uname = $("#active-user").attr('data-name');
    $("#" + uid).append(word);
    $(".msg-area").val('');
    // 滚动条自动定位到最底端
    wordBottom();
}

// 展示客服发送来的消息
function showUserMessage(data) {
    // if ($('#f-' + uid).length == 0) {
    //     addUser(uinfo);
    // }

    // 未读条数计数
    // if (!$('#f-' + uid).hasClass('active')) {
    //     var num = $('#f-' + uinfo.id).find('span:eq(1)').text();
    //     if (num == '') num = 0;
    //     num = parseInt(num) + 1;
    //     $('#f-' + uinfo.id).find('span:eq(1)').removeClass('layui-badge').addClass('layui-badge').text(num);
    // }

    localStorage.setItem("fromName",data.fromUsername);
    localStorage.setItem("fromId",data.fromUid);
    var word = msgFactory(data.msg, 'user', data.fromUsername);
    var uid = $("#active-user").attr('data-id');
    setTimeout(function () {
        $("#" + uid).append(word);
        // 滚动条自动定位到最底端
        wordBottom();
        showBigPic();
    }, 200);
}

// 消息发送工厂
function msgFactory(content, type, name) {
    var _html = '';
    if ('mine' == type) {
        _html += '<li class="laykefu-chat-mine">';
    } else {
        _html += '<li>';
    }
    _html += '<div class="laykefu-chat-user">';
    _html += '<img src="' + $("#active-user").attr("data-avatar") + '">';
    if ('mine' == type) {
        _html += '<cite><i>' + getDate() + '</i>' + $("#active-user").attr("data-name") + '</cite>';
    } else {
        _html += '<cite>' + name + '<i>' + getDate() + '</i></cite>';
    }

    _html += '</div><div class="laykefu-chat-text">' + replaceContent(content) + '</div>';
    _html += '</li>';

    return _html;
}

// 获取日期
function getDate() {
    var d = new Date(new Date());

    return d.getFullYear() + '-' + digit(d.getMonth() + 1) + '-' + digit(d.getDate())
        + ' ' + digit(d.getHours()) + ':' + digit(d.getMinutes()) + ':' + digit(d.getSeconds());
}

//补齐数位
function digit(num) {
    return num < 10 ? '0' + (num | 0) : num;
}

// 滚动条自动定位到最底端
function wordBottom() {
    var box = $(".chat-box");
    box.scrollTop(box[0].scrollHeight);
}

// 切换在线用户
function changeUserTab(obj) {
    obj.addClass('active').siblings().removeClass('active');
    wordBottom();
}

// 添加用户到面板
function addUser(data) {
    var add = true;
    $('.layui-nav-item').each(function(i){
        if(parseInt($(this).attr('data-id'))==data.id) {
            add =  false;
        }
    });
    if(add){
        var _html = '<li class="layui-nav-item" data-id="' + data.id + '" id="f-' + data.id +
            '" data-name="' + data.name + '" data-avatar="' + data.avatar + '" data-ip="' + data.ip + '">';
        _html += '<img src="' + data.avatar + '">';
        _html += '<span class="user-name">' + data.name + '</span>';
        _html += '<span class="layui-badge" style="margin-left:5px">0</span>';
        _html += '<i class="layui-icon close" style="display:none">ဇ</i>';
        _html += '</li>';
        // 添加左侧列表
        $("#user_list").append(_html);

        // 如果没有选中人，选中第一个
        var hasActive = 0;
        $("#user_list li").each(function(){
            if($(this).hasClass('active')){
                hasActive = 1;
            }
        });

        var _html2 = '';
        _html2 += '<ul id="u-' + data.id + '">';
        _html2 += '</ul>';
        // 添加主聊天面板
        $('.chat-box').append(_html2);

        if(0 == hasActive){
            $("#user_list").find('li').eq(0).addClass('active').find('span:eq(1)').removeClass('layui-badge').text('');
            $("#u-" + data.id).show();

            var id = $(".layui-unselect").find('li').eq(0).data('id');
            var name = $(".layui-unselect").find('li').eq(0).data('name');
            var ip = $(".layui-unselect").find('li').eq(0).data('ip');
            var avatar = $(".layui-unselect").find('li').eq(0).data('avatar');

            // 设置当前会话用户
            $("#active-user").attr('data-id', id).attr('data-name', name).attr('data-avatar', avatar).attr('data-ip', ip);

            $("#f-user").val(name);
            $("#f-ip").val(ip);

            $.getJSON('/Houtailogin/chat/getCity', {ip: ip}, function(res){
                $("#f-area").val(res.data);
            });
        }

        getChatLog(data.id, 1);

        checkUser();
    }

}
// 操作新连接用户的 dom操作
function checkUser() {

    $(".layui-unselect").find('li').unbind("click"); // 防止事件叠加
    // 切换用户
    $(".layui-unselect").find('li').bind('click', function () {
        changeUserTab($(this));
        var uid = $(this).data('id');
        var avatar = $(this).data('avatar');
        var name = $(this).data('name');
        var ip = $(this).data('ip');
        // 展示相应的对话信息
        $('.chat-box ul').each(function () {
            if ('u-' + uid == $(this).attr('id')) {
                $(this).addClass('show-chat-detail').siblings().removeClass('show-chat-detail').attr('style', '');
                return false;
            }
        });

        // 去除消息提示
        $(this).find('span').eq(1).removeClass('layui-badge').text('');

        // 设置当前会话的用户
        $("#active-user").attr('data-id', uid).attr('data-name', name).attr('data-avatar', avatar).attr('data-ip', ip);

        // 右侧展示详情
        $("#f-user").val(name);
        $("#f-ip").val(ip);
        $.getJSON('/service/index/getCity', {ip: ip}, function(res){
            $("#f-area").val(res.data);
        });

        getChatLog(uid, 1);

        wordBottom();
    });
}

// 删除用户聊天面板
function delUser(data) {
    $("#f-" + data.id).remove(); // 清除左侧的用户列表
    $('#u-' + data.id).remove(); // 清除右侧的聊天详情
}

// 发送快捷语句
function sendWord(obj) {
    var msg = $(obj).data('word');
    sendMessage(msg);
}

// 获取聊天记录
function getChatLog(uid, page, flag) {

    $.getJSON('/service/index/getChatLog', {uid: uid, page: page}, function(res){
        if(1 == res.code && res.data.length > 0){

            if(res.msg == res.total){
                var _html = '<div class="layui-flow-more">没有更多了</div>';
            }else{
                var _html = '<div class="layui-flow-more"><a href="javascript:;" data-page="' + parseInt(res.msg + 1)
                    + '" onclick="getMore(this)"><cite>更多记录</cite></a></div>';
            }

            var len = res.data.length;

            for(var i = 0; i < len; i++){
                var v = res.data[len - i - 1];
                if ('mine' == v.type) {
                    _html += '<li class="laykefu-chat-mine">';
                } else {
                    _html += '<li>';
                }
                _html += '<div class="laykefu-chat-user">';
                _html += '<img src="' + v.from_avatar + '">';
                if ('mine' == v.type) {
                    _html += '<cite><i>' + v.time_line + '</i>' + v.from_name + '</cite>';
                } else {
                    _html += '<cite>' + v.from_name + '<i>' + v.time_line + '</i></cite>';
                }
                _html += '</div><div class="laykefu-chat-text">' + replaceContent(v.content) + '</div>';
                _html += '</li>';
            }

            setTimeout(function () {
                // 滚动条自动定位到最底端
                if(typeof flag == 'undefined'){
                    $("#u-" + uid).html(_html);
                    wordBottom();
                }else{
                    $("#u-" + uid).prepend(_html);
                }

                showBigPic();
            }, 100);
        }
    });
}

// 显示大图
function showBigPic(){

    $(".layui-laykefu-photos").on('click', function () {
        var src = this.src;
        layer.photos({
            photos: {
                data: [{
                    "alt": "大图模式",
                    "src": src
                }]
            }
            , shade: 0.5
            , closeBtn: 2
            , anim: 0
            , resize: false
            , success: function (layero, index) {

            }
        });
    });
}

// 获取更多的的记录
function getMore(obj){
    $(obj).remove();
    var page = $(obj).attr('data-page');
    var uid = $(".layui-unselect").find('.active').data('id');
    getChatLog(uid, page, 1);
}
