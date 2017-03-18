package cn.karent.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * Created by wan on 2017/3/11.
 */
@Entity
public class DailyJoke extends Base {

    private Integer type;

    @Lob
    private String content;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
