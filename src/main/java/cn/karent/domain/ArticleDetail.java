package cn.karent.domain;

/**
 * Created by wan on 2017/3/12.
 */
public class ArticleDetail extends ArticleBase {

    private Integer viewCount;

    private Integer commentCount;

    private String content;

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
