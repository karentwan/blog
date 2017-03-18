package cn.karent.service.impl;

import cn.karent.dao.CategoryDao;
import cn.karent.entity.Category;
import cn.karent.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by wan on 2017/3/13.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category getByName(String name) {
        return categoryDao.getByName(name);
    }
}
