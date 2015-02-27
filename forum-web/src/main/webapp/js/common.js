/**
 * 多条或者单条删除
 * @param val 要删除的条件值单条用到，多条可不选
 * @param obj 多条选择的对象
 * @param url 异步删除的地址
 */
function delAll(val,obj,url){
    var ids = new Array();
    if(val!=null&&val!=""){
        ids.push(val);
    }else{
        obj.each(function(index,obj){
            if(obj.checked){
                ids.push(obj.value);
            }
        });
        if(ids.length<=0){
            alert("请至少选择一条记录！");
            return;
        }
    }
    if(confirm("确定要删除"+ ids.length +"条记录吗？")){
        $.ajax({
            type:"POST",
            url:url,
            async:false,
            data:{
                ids:ids.join(","),
                time:new Date().getTime()
            },
            success:function(msg){
                if(msg.success){
                    //$("#selForm").submit();
                	location.reload(true);
                }else{
                    alert(msg.info);
                }
            },
            error:function(){
                alert("系统响应异常！");
            },
            dataType:"json"
        });
    }
}