/***************************************************
 * Created by wan on 2017/2/8.
 ***************************************************/

/**
 * 从url中获取参数
 */
function getParamFromUrl(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)
        return  decodeURI(r[2]);
    return null;
}

//扩展String，使其具有占位符功能
String.prototype.format = function() {
    if(arguments.length==0) return this;
    for(var s=this, i=0; i<arguments.length; i++)
        s=s.replace(new RegExp("\\{"+i+"\\}","g"), arguments[i]);
    return s;
}
