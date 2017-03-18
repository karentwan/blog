package cn.karent.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wan on 2017/3/11.
 */
@Entity
public class User extends Base {

    @Column(unique = true, nullable = false)
    private String username;

    private String password;
    //用户头像
    private String userPic;

    @OneToMany(mappedBy = "user")
    private Set<Article> articles = new HashSet<Article>();

    @OneToMany
    @JoinColumn(name = "userId")
    private Set<BkMusic> musics = new HashSet<BkMusic>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public Set<BkMusic> getMusics() {
        return musics;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }
}
