/**
 * Created by wan on 2017/3/5.
 * 显示内容的技术
 */
//因为内容是使用angular动态刷新出来的数据，
//layui的代码显示也是动态将pre标签转换，所以angular绑定的数据源不能体现出代码风格
layui.use(['element', 'code'], function() {
    layui.code({
        elem:'pre',
        height:'100px',
    });
});

var urlConfig = {
    "relativeUrl":"/api/common/article/obtainRelativeArticle?page=0&size=5&name={0}",
    "contentUrl":"/api/common/article/obtainArticleDetail?id={0}&name={1}"
};

var id = getParamFromUrl("id");
var name = getParamFromUrl("name");

var app = angular.module("app", []);

/**
 * 显示文章内容
 */
app.controller("contentController", function($scope, $http, $sce){
    //记录当前文章的种类名称
    $scope.category = name;

    var url = urlConfig.contentUrl.format(id, name);

    $http.get(url).then(function (resp) {
        var status = resp.status;
        if( status == 200) {
            var data = resp.data;
            if( data.code == 200) {
                $scope.content = data.data.current;
                $scope.content.time = date('Y-m-d', $scope.content.time);
                //必须加，否则内容将会变成字符串就不会有样式了
                $scope.content.content = $sce.trustAsHtml( $scope.content.content);
//                 $scope.previous = data.data.previous;
//                 $scope.next = data.data.next;
                //将链接地址存放在$scope里面
                var url = "content.html?id={0}&name={1}";
                if( data.data.previous ) {
                    $scope.previous = {};
                    $scope.previous.url = url.format(data.data.previous.id, name);
                    $scope.previous.title = data.data.previous.title;
                }
                if( data.data.next ) {
                    $scope.next = {};
                    $scope.next.url = url.format(data.data.next.id, name);
                    $scope.next.title = data.data.next.title;
                }
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

    $scope.category = name;

    var url = urlConfig.relativeUrl.format(name);

    $http.get(url).then(function(resp){
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

