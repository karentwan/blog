package cn.karent.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by wan on 2017/3/11.
 */
@Entity
@Table(name = "comment")
public class Comment extends Base {

    @ManyToOne
    @JoinColumn(name = "articleId")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "fromUserId")
    private User fromUser;

    @OneToMany
    @JoinColumn(name = "commentId")
    private Set<Reply> replies;

    private String content;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public Set<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
