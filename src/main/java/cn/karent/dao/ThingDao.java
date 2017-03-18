package cn.karent.dao;

import cn.karent.entity.Thing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wan on 2017/3/15.
 */
@Repository
public interface ThingDao extends JpaRepository<Thing, Long>{

}
