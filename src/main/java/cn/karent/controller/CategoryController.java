package cn.karent.controller;

import cn.karent.domain.Response;
import cn.karent.entity.Category;
import cn.karent.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wan on 2017/3/13.
 */
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/api/common/allCategory")
    @ResponseBody
    public Response allCateogy() {
        Response resp = new Response();
        List<Category> list = categoryService.findAll();
        List<String> data = new ArrayList<String>();
        for(Category c : list) {
            data.add(c.getName());
        }
        resp.setData(data);
        return resp;
    }

}
