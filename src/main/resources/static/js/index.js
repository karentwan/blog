/**
 * Created by wan on 2017/3/4.
 * 创建应用
 */
var config = {
    "dailyUrl":"json/daily.json",
    "newArticle":"/api/common/article/obtainNewArticle?page=0&size=7",
    "indexList":"/api/common/article/obtainSmallPic?page=0&size=5"
}

/**
 * 导航栏需要启用element模块，例如hover时候的下划线，子菜单等等功能
 */
layui.use('element', function() {

});

var app = angular.module("app", []);
//日常笑话模块
app.controller("daily", function($scope, $http) {
    $http.get(config.dailyUrl).then(function(resp){
        var status = resp.status;
        if( status == 200) {
            var data = resp.data;
            var daily = {};
            if( data.code == 200) {
                var sentence = data.data;
                daily.joke = sentence[0];
            }
            $scope.daily = daily;
        }
        // console.log(code);
    }).catch(function(resp) {
        console.log("error:" + resp);
    });
});

//显示文章列表的控制器
app.controller("articleList", function($scope, $http) {
    // console.log("articleList:" + $scope.daily);
    console.log("articleList");
    $http.get(config.indexList).then(function(resp){
        var status = resp.status;
        if( status == 200) {
            var data = resp.data;
            if( data.code == 200) {
                $scope.list = data.data;
                var list = data.data;
                for(var i in list) {
                    list[i].time = date('Y-m-d', list[i].time);
                }
            }
        }
    }).catch(function(resp) {
console.log(resp);
    });
});

//显示最新文章列表
app.controller("newArticle", function($scope, $http){
    console.log("newArticle");
    $http.get(config.newArticle).then(function(resp){
        var status = resp.status;
        if( status == 200) {
            var data = resp.data;
            if( data.code == 200) {
                $scope.news = data.data;
            }
        }
    }).catch(function(resp) {
        console.log(resp);
    });
});