package cn.karent.entity;

import javax.persistence.Entity;

/**
 * Created by wan on 2017/3/11.
 */
@Entity
public class BkMusic extends Base {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
