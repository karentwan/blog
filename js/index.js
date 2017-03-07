/**
 * Created by wan on 2017/3/4.
 * 创建应用
 */
var config = {
    "dailyUrl":"json/index/daily.json",
    "newArticle":"json/index/newArticle.json",
    "indexList":"json/index/indexList.json"
}
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
                daily.motto = sentence[1];
                daily.famous = sentence[2];
// console.log(daily);
// console.log(data.code);
            }
            $scope.daily = daily;
        }
        console.log(code);
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