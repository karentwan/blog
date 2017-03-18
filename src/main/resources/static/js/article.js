/**
 * Created by wan on 2017/3/13.
 */
var app = angular.module('app', []);
//获取富文本编辑器的内容
var getContent = null;
//获取选择框的内容
var getSelect = null;
//layui的弹出框
var msg = null;
//用户的userId
var userId = null;

layui.use(['upload', 'layedit', 'form','layer'], function() {
    //哪个上传图片的input
    var which = null;
    layui.upload({
        url:"/api/common/upload",
        before:function(input) {
            //显示图片上传进度条
            // console.log(input);
            which = $(input).parent().parent().siblings().first();
        },
        success:function(resp) {
            console.log("图文上传成功!");
            //将数据添加至路径上面去
            if( resp.code == 0) {
                var path = resp.data.src;
                $(which).val(path);
            }
        }
    });
    //创建富文本编辑器
    var layedit = layui.layedit;
    var index = layedit.build('content', {
        height:900,
        uploadImage: {
            url:"/api/common/upload",
            type:"POST"
        }
    });
    //闭包的方式
    getContent = function() {
        return layedit.getContent(index);
    }
    //form表单的交互
    var form = layui.form();
    form.on('select(category)', function(data) {
// console.log(data.value); //得到被选中的值
        var s = data.value;
        //去除前面的string:
        getSelect(s.substring(7, s.length));
    });

    //弹出框
    var layer = layui.layer;
    msg = function(s) {
        layer.msg(s);
    }

});

//导航栏
var nav = app.controller('nav', function($scope, $http){
    $http.get("/api/common/currentUser").then(function(resp){
console.log("nav");
        if( resp.status == 200) {
            var data = resp.data;
            if( data.code == 200) {
                $scope.user = data.data;
                userId = data.data.id;
            }
        }
    }).catch( function(resp) {

    });
});

var content = app.controller('content', function($scope, $http) {
    $scope.data = {};
    //初始化
    $http.get("/api/common/allCategory").then(function(resp){
        var status = resp.status;
        if( status == 200) {
            var data = resp.data;
            if( data.code == 200) {
                var d = data.data;
                $scope.categories = d;
                // console.log(d);
            }
        }
    }).catch(function(resp) {
       console.log(resp);
    });

    //按钮点击事件
    $scope.click = function() {
        var content = getContent();
        //TODO 用户的userId应该从后台获取
        $scope.data.userId = userId;
        $scope.data.content = content;
        $scope.data.imgPic = $("#imgPic").val();
        $scope.data.backPic = $("#backPic").val();
        //select选中的种类
        // $scope.data.categoryName = getSelect;
        console.log($scope.data);
        //访问网络请求
        $http({
            method:"POST",
            url:"/api/common/article/addNewArticle",
            data:$scope.data,
            headers:{'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function(obj) {
                var str = [];
                for(var p in obj){
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                }
                return str.join("&");
            }
        }).then(function(resp) {
// console.log(resp);
            if( resp.status == 200 ) {
                var data = resp.data;
                if( data.code == 200) {
                    msg("文章添加成功!");
                }
                else
                    msg("文章添加失败");
            }
        }).catch(function(resp) {
            console.log(resp);
        });
    }

    //闭包方式更改内部的数据
    getSelect = function(s) {
        $scope.data.categoryName = s;
        //通知数据改变
        $scope.$apply();
        // console.log("angular:" + $scope.data.categoryName);
    }
});