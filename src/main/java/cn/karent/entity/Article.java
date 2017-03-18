package cn.karent.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wan on 2017/3/11.
 * 文章实体
 */
@Entity
@Table(name = "article")
public class Article extends Base {

    private String title;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;

    private Integer readNumber;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String imgPath;

    private String brief;

    private String showUrl;

    private String downloadUrl;

    private String bkPic;

    @OneToMany(mappedBy = "article")
    private Set<Comment> comments = new HashSet<Comment>();

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReadNumber() {
        return readNumber;
    }

    public void setReadNumber(Integer readNumber) {
        this.readNumber = readNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getShowUrl() {
        return showUrl;
    }

    public void setShowUrl(String showUrl) {
        this.showUrl = showUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getBkPic() {
        return bkPic;
    }

    public void setBkPic(String bkPic) {
        this.bkPic = bkPic;
    }
}
