/**
 * Created by wan on 2017/3/5.
 */
var search = $("div.nav span.search-btn");
search.hover(function() {
    search.hasClass("unfocus") ? search.removeClass("unfocus") : null;
    search.addClass("focus");
}, function() {
    search.removeClass("focus");
    search.addClass("unfocus");
    //一秒之后删除unfocus类
    setTimeout("search.removeClass('unfocus')", 1000);
});