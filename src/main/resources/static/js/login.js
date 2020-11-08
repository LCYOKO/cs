layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;


    //登录按钮
    form.on("submit(login)",function(data){

        var username=$("#loginName").val();
        var password=$("#loginPassword").val();
        $.ajax({
            url:'/login',
            dataType: 'json',
            data:{miliao:username,password:password},
            success:function (res) {
                console.log(res);
                if(res===undefined){
                    openLayer("未知错误");
                }
                else if(res.code!==2000) openLayer(res.msg);
                else {
                    console.log(res);
                    window.location.href=res.data;
                }

            }

        })

        function openLayer(msg) {
            //console.log(msg);
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
