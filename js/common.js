/**
 * Created by wan on 2017/3/5.
 */
var search = $("div.nav span.search-btn");
search.hover(function() {
    search.addClass("focus");
}, function() {
    search.val();
    search.removeClass("focus");
});