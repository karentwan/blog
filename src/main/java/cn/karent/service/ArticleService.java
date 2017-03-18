package cn.karent.service;

import cn.karent.domain.ArticleBase;
import cn.karent.domain.LargePic;
import cn.karent.domain.SmallPic;
import cn.karent.domain.WithoutPic;
import cn.karent.entity.Article;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

/**
 * Created by wan on 2017/3/11.
 */
public interface ArticleService {

    List<SmallPic> getArticleWithSmallPic(Pageable pageable);

    Object getArticleWithLargePic(Pageable pageable, String name);

    Object getArticle(Pageable pageable, String name);

    List<ArticleBase> getRelativeArticle(Pageable pageable, String name);

    List<ArticleBase> getNewArticle(Pageable pageable);

    Map<String, Object> getById(Long id, String name);

    Article save(Article a);

    void delete(Long id);

}
