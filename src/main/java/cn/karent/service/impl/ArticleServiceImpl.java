package cn.karent.service.impl;

import cn.karent.dao.ArticleDao;
import cn.karent.domain.*;
import cn.karent.entity.Article;
import cn.karent.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wan on 2017/3/11.
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ArticleDao articleDao;

    @Override
    public List<SmallPic> getArticleWithSmallPic(Pageable pageable) {
//        Page<Article> articles = articleDao.findAll(pageable);
        Page<Article> articles = articleDao.findAllWithoutCode(pageable, "代码实例");
        List<Article> list = articles.getContent();
        List<SmallPic> data = new ArrayList<SmallPic>();
        for(Article a : list) {
            SmallPic s = new SmallPic();
            s.setId(a.getId());
            String path = a.getImgPath();
            if( path != null && !path.equals("")) {
                s.setPicPath(path);
                s.setHasPic("");
            } else {
                s.setHasPic("no-pic");
            }
            s.setTitle(a.getTitle());
            s.setAuthor(a.getUser().getUsername());
            s.setTime(a.getCreateTime());
            s.setCategory(a.getCategory().getName());
            //TODO 待商榷，影响性能
            s.setCommentCount(a.getComments().size());
            s.setBrief(a.getBrief());
            data.add(s);
        }
        return data;
    }

    @Override
    public Object getArticleWithLargePic(Pageable pageable, String name) {
        Page<Article> articles = articleDao.findByCategory(pageable, name);
        List<Article> list = articles.getContent();
        List<LargePic> data = new ArrayList<LargePic>();
        for(Article a : list) {
            LargePic l = new LargePic();
            l.setId(a.getId());
            l.setTitle(a.getTitle());
            l.setAuthor(a.getUser().getUsername());
            l.setTime(a.getCreateTime());
            l.setBrief(a.getBrief());
            l.setBackpic(a.getBkPic());
            l.setDownloadUrl(a.getDownloadUrl());
            l.setShowUrl(a.getShowUrl());
            data.add(l);
        }
        Map<String, Object> map = getContentFromList(data, articles);
        return map;
    }

    @Override
    public Object getArticle(Pageable pageable, String name) {
        Page<Article> articles = articleDao.findByCategory(pageable, name);
        List<Article> list = articles.getContent();
        List<WithoutPic> data = new ArrayList<WithoutPic>();
        for(Article a : list) {
            WithoutPic w = new WithoutPic();
            w.setId(a.getId());
            w.setTitle(a.getTitle());
            w.setAuthor(a.getUser().getUsername());
            w.setTime(a.getCreateTime());
            w.setCategory(a.getCategory().getName());
            w.setViewCount(a.getReadNumber());
            w.setUserpic(a.getUser().getUserPic());
            //TODO 待商榷，影响性能
            w.setCommentCount(a.getComments().size());
            data.add(w);
        }
        Map<String, Object> map = getContentFromList(data, articles);
        return map;
    }

    private <T, M> Map<String, Object> getContentFromList(List<T> list, Page<M> articles) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("content", list);
        map.put("totalElements", articles.getTotalElements());
        map.put("totalPages", articles.getTotalPages());
        map.put("last", articles.isLast());
        map.put("number", articles.getNumber());
        map.put("size", articles.getSize());
        map.put("first", articles.isFirst());
        map.put("numberOfElements", articles.getNumberOfElements());
        return map;
    }

    @Override
    public List<ArticleBase> getRelativeArticle(Pageable pageable, String name) {
        Page<Article> articles = articleDao.findByCategory(pageable, name);
        List<ArticleBase> data = new ArrayList<ArticleBase>();
        for(Article a : articles) {
            ArticleBase ab = new ArticleBase();
            ab.setId(a.getId());
            ab.setTitle(a.getTitle());
            data.add(ab);
        }
        return data;
    }

    @Override
    public List<ArticleBase> getNewArticle(Pageable pageable) {
        Page<Article> articles = articleDao.findArticleOrderByCreateTimeDesc(pageable, "代码实例");
        List<ArticleBase> data = new ArrayList<ArticleBase>();
        for(Article a : articles) {
            ArticleBase ab = new ArticleBase();
            ab.setId(a.getId());
            ab.setTitle(a.getTitle());
            data.add(ab);
        }
        return data;
    }

    @Override
    public Map<String, Object> getById(final Long id, String name) {
        final Map<String, Object> map = new HashMap<String, Object>();
        //TODO 每一次获取文章详情都应该将阅读数加一
        Article article = articleDao.getOne(id);
        //文章查看人数加1
        Integer number = article.getReadNumber();
        if( number == null)
            number = 1;
        else
            number = article.getReadNumber() + 1;
        article.setReadNumber(number);
        //查询上下文相关的属性
        String sql = "SELECT u.id, u.title FROM (SELECT a.id, a.title FROM article AS " +
                "a LEFT JOIN category AS c ON a.category_id = c.id WHERE " +
                "c.name= ? AND a.id < ? ORDER BY a.id DESC LIMIT 1 ) AS u" +
                " UNION " +
                " SELECT u.id, u.title FROM (SELECT a.id, a.title FROM article " +
                "AS a LEFT JOIN category AS c ON a.category_id = c.id WHERE c.name= ? AND a.id > " +
                "? LIMIT 1) AS u";
//System.out.println("sql:" + sql);
        jdbcTemplate.query(sql, new Object[]{name, id, name, id}, new RowCallbackHandler() {
            int i = 0;
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                ArticleBase a = new ArticleBase();
                a.setId(resultSet.getLong(1));
                a.setTitle(resultSet.getString(2));
//                if( i++ == 0) {
//                    map.put("previous", a);
//                } else {
//                    map.put("next", a);
//                }
                //大于，下一篇文章
                if( a.getId() > id) {
                    map.put("next", a);
                    //小于，上一篇文章
                } else if(a.getId() < id){
                    map.put("previous", a);
                }
            }
        });
        ArticleDetail ad = new ArticleDetail();
        ad.setId(article.getId());
        ad.setTitle(article.getTitle());
        String con = article.getContent();
        ad.setContent(con);
        //TODO 此处应该优化
        ad.setCommentCount(article.getComments().size());
        ad.setViewCount(article.getReadNumber());
        ad.setAuthor(article.getUser().getUsername());
        ad.setTime(article.getCreateTime());
        map.put("current", ad);
        return map;
    }

    @Override
    public Article save(Article a) {
        return articleDao.save(a);
    }

    @Override
    public void delete(Long id) {
        articleDao.delete(id);
    }
}
