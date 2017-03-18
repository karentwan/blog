package cn.karent.dao;

import cn.karent.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wan on 2017/3/13.
 */
@Repository
public interface CategoryDao extends JpaRepository<Category, Long>{

    Category getByName(String name);

}
