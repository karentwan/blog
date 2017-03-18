package cn.karent.dao;

import cn.karent.entity.DailyJoke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wan on 2017/3/13.
 */
@Repository
public interface DailyJokeDao extends JpaRepository<DailyJoke, Long> {

}
