package cn.karent.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by wan on 2017/3/11.
 */
@MappedSuperclass
public class Base {

    @Id
    @GeneratedValue
    private Long id;

    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long craeteTime) {
        this.createTime = craeteTime;
    }
}
