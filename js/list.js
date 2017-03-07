
/**
 * Created by wan on 2017/3/7.
 */
var urlConfig = {
    "listUrl":"json/list/list.json"
}

var app = angular.module("app", []);

app.controller("listController", function($scope, $http){
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
                var pages = data.data.totalPages;
                $scope.pages = new Array;
                for(var i = 0; i < pages; i++) {
                    $scope.pages.push(i);
                }
            } else {
                alert(data.error);
            }
        }
    }).catch( function(resp){
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