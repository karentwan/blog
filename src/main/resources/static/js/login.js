/**
 * Created by wan on 2017/3/15.
 */

var msg = null;

layui.use(['element','layer'], function() {
    var layer = layui.layer;
    msg = function(s) {
        layer.msg(s);
    }
});

var app = angular.module('app', []);

var form = app.controller('form', function($scope, $http) {

    $scope.user = {};

    $scope.click = function() {
        console.log($scope.user);
        if( $scope.user.username == null) {
            msg("用户名不能为空!");
            return;
        }
        if( $scope.user.password == null) {
            msg("密码不能为空!");
            return;
        }
        //登陆
        $http({
           method:"POST",
            url:"/api/common/login",
            data:$scope.user,
            headers:{'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function(obj) {
                var str = [];
                for(var p in obj){
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                }
                return str.join("&");
            }
        }).then(function(resp) {
            if( resp.status == 200) {
                var data = resp.data;
                if( data.code == 200) {
                    //跳转到首页
                    window.location.href = "index.html";
                }
            }
        }).catch(function(resp) {
            console.log(resp);
        });
    }

});