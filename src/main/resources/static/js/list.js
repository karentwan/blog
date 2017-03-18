
/**
 * Created by wan on 2017/3/7.
 */
var urlConfig = {
    "listUrl":"/api/common/article/obtainLargePic?page={0}&size=8&categoryName=代码实例"
}

//弹出消息
var msg = null;

layui.use(['element', 'form', 'layer'], function() {
    var layer = layui.layer;
    msg = function(s) {
        layer.msg(s);
    }
});

var app = angular.module("app", []);

app.controller("listController", function($scope, $http){

    //访问网络
    var get = function(url) {
        $http.get(url).then(function(resp){
            var status = resp.status;
            if( status == 200) {
                var data = resp.data;
                if( data.code == 200) {
                    var arr = new Array;
                    var dataArr = data.data.content;
                    for(var i in dataArr) {
                        var o = {};
                        var o1 = dataArr[i];
                        o.id = o1.id;
                        o.title = o1.title;
                        o.backpic = o1.backpic;
                        o.author = o1.author;
                        o.brief = o1.brief;
                        o.date = date('Y-m-d', o1.time);
                        o.downloadUrl = o1.downloadUrl;
                        o.showUrl = o1.showUrl;
                        arr.push(o);
                    }
                    $scope.arr = arr;
                    //分页
                    var pages = {};
                    pages.first = data.data.first;
                    pages.last = data.data.last;
                    pages.number = data.data.number;
                    pages.totalPages = data.data.totalPages;
                    $scope.pages = pages;
                } else {
                    alert(data.error);
                }
            }
        }).catch( function(resp){
            console.log(resp);
        });
    }
    var url = urlConfig.listUrl.format(0);
    //初始化的访问网络请求
    get(url);

    /**
     * 下一页
     */
    $scope.nextPage = function() {
        var last = $scope.pages.last;
        if( last ) {
            // layui.msg("当前是最后一页，没有下一页");
            msg("当前是最后一页，没有下一页");
        } else {
            var url = urlConfig.listUrl.format($scope.pages.number + 1);
            get(url);
        }
    }
    /**
     * 上一页
     */
    $scope.previous = function() {
        var first = $scope.pages.first;
        if( first ) {
            msg("当前是第一页，没有上一页");
        } else {
            var url = urlConfig.listUrl.format($scope.pages.number - 1);
            get(url);
        }
    }

    /**
     * 首页
     */
    $scope.firstPage = function() {
        var first = $scope.pages.first;
        if( first ) {
            msg("当前是首页");
        } else {
            var url = urlConfig.listUrl.format(0);
            get(url);
        }
    }

    /**
     * 最后一页
     */
    $scope.lastPage = function() {
        var last = $scope.pages.last;
        if( last ) {
            msg("当前是最后页");
        } else {
            var url = urlConfig.listUrl.format($scope.pages.totalPages - 1);
            get(url);
        }
    }

});