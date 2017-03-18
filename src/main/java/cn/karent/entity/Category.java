package cn.karent.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wan on 2017/3/11.
 */
@Entity
public class Category extends Base {

    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Article> articles = new HashSet<Article>();

    public Set<Article> getArticles() {
        return articles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
