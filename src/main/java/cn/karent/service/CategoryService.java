package cn.karent.service;

import cn.karent.entity.Category;
import java.util.List;

/**
 * Created by wan on 2017/3/13.
 */
public interface CategoryService {

    List<Category> findAll();

    Category getByName(String name);

}
