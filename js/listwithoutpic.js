/**
 * Created by wan on 2017/3/7.
 */

var urlConfig = {
    "listUrl":"json/listwithoutpic/list.json",
    "relativeUrl":"json/listwithoutpic/relative.json"
};

var app = angular.module("app", []);
//显示文章列表的控制器
app.controller("list", function($scope, $http) {
    $http.get(urlConfig.listUrl).then(function(resp){
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
                var pages = data.data.totalPages;
                $scope.pages = new Array;
                for(var i = 0; i < pages; i++) {
                    $scope.pages.push(i);
                }
            } else {
                console.log(status);
            }
        }
    }).catch(function(resp){
console.log(resp);
    });
    /**
     * 下一页
     */
    $scope.nextPage = function() {
console.log("下一页");
    }
    /**
     * 上一页
     */
    $scope.previous = function() {
console.log("上一页");
    }

    /**
     * 首页
     */
    $scope.firstPage = function() {
console.log("首页");
    }

    /**
     * 最后一页
     */
    $scope.lastPage = function() {
console.log("最后一页");
    }


});
//相关文章的列表
app.controller("relative", function($scope, $http) {
    $http.get(urlConfig.relativeUrl).then(function(resp){
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