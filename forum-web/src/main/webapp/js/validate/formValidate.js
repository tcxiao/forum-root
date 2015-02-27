/**
 * Created by 李晓兵 on 14-7-8.
 */
//为空验证，
function checkBlank(obj){
    if(obj.val()==""&&!obj.attr("disabled")){
        obj.alert("必填字段");
        return false;
    }
    return true;
}
//年份验证
function checkYear(obj){
    if(obj.val()==""){//为空不验证
        return true;
    }
    var isYear=/^[1-9]\d{3}$/;
    if(!isYear.test(obj.val())){
        obj.alert("年份格式错误，如YYYY");
        return false;
    }
    if(obj.val()>new Date().getFullYear()){
        obj.alert("不能大于当前年度");
        return false;
    }
    return true;
}

//检查月份
function checkMonth(obj){
    var mouthVal=obj.val();
    if(mouthVal==""){//为空不验证
        return true;
    }
    if(mouthVal>0 && mouthVal<13){
        return true;
    }else{
        obj.alert("月份格式错误！");
        return false;
    }
}
//长度验证
function checkLength(obj,len){
    if(obj.val()==""){//为空不检查
        return true;
    }
    if(strlen(obj.val())>len){
        obj.alert("长度不能大于"+len);
        return false;
    }
    return true;
}
//年度比较
function valCompare(curObj,comObj){
    if(curObj.val()<comObj.val()){
        curObj.alert("结束年度小于起始年度");
        return false;
    }
    return true;
}
//整数验证
function checkIsInt(obj){
    if(obj.val()==""){//为空不验证
        return true;
    }
    var isInt=/^[1-9]\d*$/;
    if(!isInt.test(obj.val())){
        obj.alert("必须为整数！");
        return false;
    }
    return true;
}

/**
 * 邮箱验证
 * @param obj
 * @returns {boolean}
 */
function checkEmail(obj){
	if(obj.val()==""){
		return true;
	}
    var isEmail=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if(!isEmail.test(obj.val())){
        obj.alert("Email格式错误！");
        return false;
    }
    return true;
}

/**
 * 手机验证
 * @returns {boolean}
 */
function checkMobPhone(obj){
    var isMobPhone=/^1[358]\d{9}$/;
    if(!isMobPhone.test(obj.val())){
        obj.alert("手机格式错误！");
        return false;
    }
    return true;
}

/**
 * 电话号码验证
 * @returns {boolean}
 */
function checkTel(obj){
    var isTel=/^(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-?(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/;
    if(!isTel.test(obj.val())){
        obj.alert("电话格式错误！");
        return false;
    }
    return true;
}

/**
 * 复选框选择验证
 * @param obj
 * @returns {boolean}
 */
function checkCheckBox(obj){
    var flag=false;
    obj.each(function(){
       if(this.checked){
           flag=true;
       }
    });
    if(!flag){
        $(obj[obj.length-1]).alert("请至少选择一项！");
        return false;
    }
    return true;
}

/**
 * 远程验证
 */
function checkRemote(obj,objId,url){
    if(obj.val()==""){//为空不验证
        return true;
    }
    var msg="已存在！";
    var flag=false;
    var objName=obj.attr("name");
    var objIdName=objId.attr("name");
    var objVal=obj.val();
    var objIdVal=objId.val();
    var data="({"+ objName +":\""+ objVal +
        "\","+ objIdName +":\""+ objIdVal +
        "\",times:\""+ new Date().getTime() +"\"})";
    $.ajax({
        type:"POST",
        url:url,
        async:false,
        data:eval(data),
        success:function(msg){
            flag=msg.success;
        },
        error:function(){
            msg="系统响应异常！";
        },
        dataType:'json'
    });
    if(!flag){
        obj.alert(msg);
    }
    return flag;
}

/**
 * 验证是否为一个小数
 * @param obj
 * @returns {boolean}
 */
function isDecimals(obj){
    if(obj.val()==""){//为空不验证
        return true;
    }
    var isDec=/^[1-9]\d*(\.\d+)?$/;
    if(!isDec.test(obj.val())){
        obj.alert("数字非法！");
        return false;
    }
    return true;
}

/**
 * 验证小数的长度
 * @param obj 当前对象
 * @param befLen 整数位
 * @param aftLen 小数位
 * @returns {boolean}
 */
function checkDecLen(obj,befLen,aftLen){
    var flag=true;
    if(obj.val()!=""){//为空不验证
        var str=obj.val().split(".");
        if(str[0].length>befLen){
            flag=false;
        }else if(str.length>1&&str[1].length>aftLen){
            flag=false;
        }
    }
    if(!flag){
        obj.alert("整数"+befLen+"位，小数"+aftLen+"位");
    }
    return flag;
}

/*字符长度验证*/
function strlen(str){
    var len = 0;
    for (var i=0; i<str.length; i++) {
        var c = str.charCodeAt(i);
        //单字节加1
        if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {
            len++;
        }
        else {
            len+=2;
        }
    }
    return len;
}

/**
 * 密码一致验证
 * @param curObj
 * @param tarObj
 * @returns {Boolean}
 */
function equalTo(curObj,tarObj){
	var flag=true;
	if(curObj.val()!=""){
		if(curObj.val()!=tarObj.val()){
			flag=false;
			curObj.alert("密码不一致");
		}
	}
	return flag;
}

function checkArea(areaJqueryObject){
    if(areaJqueryObject.val()==0){
        areaJqueryObject.alert("请选择!");
        return false;
    }
    return true;
}