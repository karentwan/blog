package cn.karent.dao;

import cn.karent.entity.BkMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wan on 2017/3/14.
 */
@Repository
public interface MusicDao extends JpaRepository<BkMusic, Long>{

}
