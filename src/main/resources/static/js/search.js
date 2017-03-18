/**
 * Created by wan on 2017/3/14.
 */

var nav = app.controller('nav', function($scope) {
    $scope.click = function() {
        var url ="listwithoutpic.html?categoryName={0}";
        url = url.format($scope.name);
        window.location.href = url;
    }
});