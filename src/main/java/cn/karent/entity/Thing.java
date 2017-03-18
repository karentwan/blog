package cn.karent.entity;

import javax.persistence.Entity;

/**
 * Created by wan on 2017/3/15.
 */
@Entity
public class Thing extends Base{

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
