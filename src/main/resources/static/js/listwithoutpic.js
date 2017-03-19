/**
 * Created by wan on 2017/3/7.
 */

var urlConfig = {
    "listUrl":"/api/common/article/obtainWithoutPic?size=10&page={0}&categoryName={1}",
    "relativeUrl":"/api/common/article/obtainRelativeArticle?page=0&size=5&name={0}"
};
//弹出消息
var msg = null;
//获取当前的种类
var category = getParamFromUrl("categoryName");

layui.use(['element', 'form', 'layer'], function() {
    var layer = layui.layer;
    msg = function(s) {
        layer.msg(s);
    }
});
//初始化导航栏
(function(){
    var items = $(".nav .layui-nav .layui-nav-item");
    if( !category ) {
        items.first().addClass("layui-item");
    } else {
        items.each(function() {
            console.log($(this).text());
            if( $(this).hasClass('layui-this'))
                $(this).removeClass('layui-this');
            var text = $(this).text().trim();
            if( category == text) {
                $(this).addClass('layui-this');
                return;
            }
        })
    }
})();

var app = angular.module("app", []);
//显示文章列表的控制器
app.controller("list", function($scope, $http) {
console.log("category:" +category);
    $scope.category = category;
    // urlConfig.listUrl += category;
    var url = urlConfig.listUrl.format(0, category);
    //访问网络的函数
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
                        o.userPic = o1.userpic;
                        o.author = o1.author;
                        o.category = o1.category;
                        o.commentCount = o1.commentCount;
                        o.viewCount = o1.viewCount;
                        o.date = date('Y-m-d', o1.time);
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
                    console.log($scope.pages);
                } else {
                    console.log(status);
                }
            }
        }).catch(function(resp){
            console.log(resp);
        });
    }
    //初始化
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
            var url = urlConfig.listUrl.format($scope.pages.number + 1, category);
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
            var url = urlConfig.listUrl.format($scope.pages.number - 1, category);
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
            var url = urlConfig.listUrl.format(0, category);
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
            var url = urlConfig.listUrl.format($scope.pages.totalPages - 1, category);
            get(url);
        }
    }
});
//相关文章的列表
app.controller("relative", function($scope, $http) {
    // urlConfig.relativeUrl += category;
    var url = urlConfig.relativeUrl.format(category);
    $http.get(url).then(function(resp){
        var status = resp.status;
        if( status == 200) {
            var data = resp.data;
            if( data.code == 200) {
                $scope.arr = data.data;
            } else {
                alert(data.error);
            }
        }
    }).catch(function(resp){
       console.log(resp);
    });
});

app.controller("head", function($scope) {
    $scope.category = category;
});

