package cn.karent.controller;

import cn.karent.domain.*;
import cn.karent.entity.Article;
import cn.karent.entity.Category;
import cn.karent.entity.User;
import cn.karent.service.ArticleService;
import cn.karent.service.CategoryService;
import cn.karent.service.UserService;
import cn.karent.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wan on 2017/3/11.
 * 文章控制器
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/api/test")
    @ResponseBody
    public String test() {
System.out.println("hello, wan");
        return "Hello, wan";
    }

    /**
     * 首页获取小图的文章，出了代码实例，其他的文章综合
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/api/common/article/obtainSmallPic")
    @ResponseBody
    public Response obtainSmallPic(@RequestParam(value = "page", required = true) Integer page,
                                   @RequestParam(value="size", required = true) Integer size) {
        Response resp = new Response();
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        List<SmallPic> data = articleService.getArticleWithSmallPic(pageable);
        resp.setData(data);
        return resp;
    }

    /**
     * 大图基本都是代码实例，所以请求应该带上代码实例这个categoryName
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/api/common/article/obtainLargePic")
    @ResponseBody
    public Response obtainLargePic(@RequestParam(value = "page", required = true) Integer page,
                                   @RequestParam(value="size", required = true) Integer size,
                                   @RequestParam(value = "categoryName", required = true) String name) {
        Response resp = new Response();
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Object data = articleService.getArticleWithLargePic(pageable, name);
        resp.setData(data);
        return resp;
    }

    @RequestMapping("/api/common/article/obtainWithoutPic")
    @ResponseBody
    public Response obtainWithoutPic(@RequestParam(value = "page", required = true) Integer page,
                                   @RequestParam(value="size", required = true) Integer size,
                                     @RequestParam(value = "categoryName", required = true) String name) {
        Response resp = new Response();
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Object data = articleService.getArticle(pageable, name);
        resp.setData(data);
        return resp;
    }

    @RequestMapping("/api/common/article/obtainNewArticle")
    @ResponseBody
    public Response obtainNewArticle(@RequestParam(value = "page", required = true) Integer page,
                                     @RequestParam(value="size", required = true) Integer size) {
        Response resp = new Response();
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        List<ArticleBase> data = articleService.getNewArticle(pageable);
        resp.setData(data);
        return resp;
    }

    @RequestMapping("/api/common/article/obtainRelativeArticle")
    @ResponseBody
    public Response obtainRelativeArticle(@RequestParam(value = "page", required = true) Integer page,
                                     @RequestParam(value="size", required = true) Integer size,
                                     @RequestParam(value = "name", required = true) String name) {
        Response resp = new Response();
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        List<ArticleBase> data = articleService.getRelativeArticle(pageable, name);
        resp.setData(data);
        return resp;
    }

    /**
     * 获取文章详情
     * @param id
     * @param name 种类名称
     * @return
     */
    @RequestMapping("/api/common/article/obtainArticleDetail")
    @ResponseBody
    public Response obtainArticleDetail(@RequestParam(value = "id", required = true) Long id,
                                        @RequestParam(value = "name", required = true) String name) {
        Response resp = new Response();
        Map<String, Object> map  = articleService.getById(id, name);
        resp.setData(map);
        return resp;
    }

    /**
     * 添加新文章
     * @param title          文章标题
     * @param content        文章内容
     * @param userId         文章所属用户
     * @param imgPic         小图地址
     * @param brief          文章简介
     * @param showUrl        展示地址
     * @param downloadUrl   下载地址
     * @param backPic        大图地址
     * @return
     */
    @RequestMapping("/api/common/article/addNewArticle")
    @ResponseBody
    public Response addNewArticle(@RequestParam(name = "title", required = true) String title,
                                  @RequestParam(name="content", required = true) String content,
                                  @RequestParam(name = "userId", required= true) Long userId,
                                  @RequestParam(name = "imgPic", required = false) String imgPic,
                                  @RequestParam(name = "brief", required = true) String brief,
                                  @RequestParam(name = "showUrl", required = false) String showUrl,
                                  @RequestParam(name= "downloadUrl", required = false) String downloadUrl,
                                  @RequestParam(name = "backPic", required = false) String backPic,
                                  @RequestParam(name="categoryName", required = true) String categoryName) {
        Response resp = new Response();
        Article article = new Article();
        article.setTitle(title);
        article.setBrief(brief);
System.out.println("content:" + content);
        article.setContent(content);
        article.setShowUrl(showUrl);
        article.setDownloadUrl(downloadUrl);
        //创建时间
        article.setCreateTime(new Date().getTime());
        User u = userService.getOne(userId);
        article.setImgPath(imgPic);
        article.setBkPic(backPic);
        article.setUser(u);
        //根据名称获取种类
        Category c = categoryService.getByName(categoryName);
        article.setCategory(c);
        articleService.save(article);
        return resp;
    }

    /**
     * 文件上传
     * @return
     */
    @RequestMapping(value = "/api/common/upload", method = RequestMethod.POST)
    @ResponseBody
    public Upload uploadFile(@RequestParam(name = "file", required = true) MultipartFile img) {
        Upload upload = new Upload();
        String path = null;
        try {
            if( !img.isEmpty()) {
                path = FileUtil.saveFile(img.getInputStream(), img.getContentType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        upload.setCode(0);
        upload.setMsg("upload seccess!");
        Map<String, String> map = new HashMap<String, String>();
        map.put("src", path);
        upload.setData(map);
        return upload;
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    @RequestMapping("api/admin/delete")
    @ResponseBody
    public Response delete(@RequestParam(value="userId", required = true) Long id) {
        Response resp = new Response();
        articleService.delete(id);
        return resp;
    }

}
