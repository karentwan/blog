/**
 * Created by wan on 2017/3/5.
 * 显示内容的技术
 */
var urlConfig = {
    "relativeUrl":"json/listwithoutpic/relative.json",
    "contentUrl":"json/content/content.json"
};

var app = angular.module("app", []);

/**
 * 显示文章内容
 */
app.controller("contentController", function($scope, $http){
    $http.get(urlConfig.contentUrl).then(function (resp) {
        var status = resp.status;
        if( status == 200) {
            var data = resp.data;
            if( data.code == 200) {
                $scope.content = data.data.current;
console.log($scope.content);
                $scope.previous = data.data.previous;
                $scope.next = data.data.next;
            }
        }
    }).catch(function(resp){
       console.log(resp);
    });
});

/**
 * 相关文章
 */
app.controller("relativeController", function($scope, $http){
    $http.get(urlConfig.relativeUrl).then(function(resp){
       var status = resp.status;
       if( status == 200) {
           var data = resp.data;
           if( data.code == 200) {
               $scope.arr = data.data;
           }
       }
    }).catch(function(resp){
        console.log(resp);
    });
});