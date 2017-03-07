/**
 * Created by wan on 2017/3/7.
 */
var urlConfig = {
    "listUrl":"json/journal/list.json"
}

var app = angular.module("app", []);

app.controller("listController", function($scope, $http){
    $http.get(urlConfig.listUrl).then(function(resp){
        var status = resp.status;
        if(  status == 200) {
            var data = resp.data;
            if(data.code == 200) {
                var arr = new Array;
                var dataArr = data.data;
                for(var i in dataArr) {
                    var o = {};
                    var o1 = dataArr[i];
                    // o.date = date('Y-m-d', o1.time);
                    o.date = "2017年02月25日";
                    o.thing = o1.thing;
                    arr.push(o);
                }
                $scope.arr = arr;
            }
        }
    }).catch(function(resp) {
        console.log(resp);
    });
});