/**
 * Created by wan on 2017/3/19.
 */

//背景音乐
var bkMusic = app.controller('bkMusic', function($scope, $http, $sce) {
    $http.get("/api/common/getRandomMusic").then(function(resp) {
        if( resp.status == 200) {
            var data = resp.data;
            if( data.code == 200) {
                $scope.path = $sce.trustAsHtml(data.data);
            }
        }
    }).catch(function(resp) {
console.log(resp);
    });
});

app.directive('music', function() {
    return {
        restrict:'AE',
        template:'<div ng-bind-html="path"></div>',
        replace:true,
        link:function(scope, ele, attrs) {
            //修改样式
            ele.css('display', 'none');
        }
    };
});
