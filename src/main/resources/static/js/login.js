layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

    $(".loginBody .seraph").click(function(){
        layer.msg("这只是做个样式，至于功能，你见过哪个后台能这样登录的？还是老老实实的找管理员去注册吧",{
            time:5000
        });
    })

    //登录按钮
    form.on("submit(login)",function(data){

        var username=$("#loginName").val();
        var password=$("#loginPassword").val();
        $.ajax({
            url:'/login',
            dataType: 'json',
            data:{username:username,passwrod:password},
            success:function (res) {
                console.log(res);
                res.msg='123123';
                if(res===undefined){
                    openLayer("未知错误");
                }
                else if(res.code!=200) openLayer(res.msg);
                else window.location.href="/";

            }

        })

        function openLayer(msg) {
            console.log(msg);
          var index=  layer.open({
                 title: '提示',
                content: msg,
                success: function(layero, index){
                    setTimeout(function () {
                    layer.close(index);
                    },2000);
                }
            });
        }
        // $(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
        // setTimeout(function(){
        //     window.location.href = "/layuicms2.0";
        // },1000);
        return false;
    })

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
})
