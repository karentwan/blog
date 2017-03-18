package cn.karent.dao;

import cn.karent.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.persistence.Table;

/**
 * Created by wan on 2017/3/11.
 */
@Repository
@Table(name = "article")
public interface ArticleDao extends JpaRepository<Article, Long>{

    @Query("select a from Article a where a.category.name <> :name order by a.createTime desc")
    Page<Article> findArticleOrderByCreateTimeDesc(Pageable pageable, @Param("name") String name);

    @Query("select a from Article a where a.category.name like :name")
    Page<Article> findByCategory(Pageable pageable, @Param("name") String name);

    //查找所有去除代码实例的文章
    @Query("select a from Article a where a.category.name <> :name")
    Page<Article> findAllWithoutCode(Pageable pageable,@Param("name") String name);


}
