package cn.karent.controller;

import cn.karent.domain.Response;
import cn.karent.entity.User;
import cn.karent.service.UserService;
import cn.karent.util.Constant;
import cn.karent.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wan on 2017/3/13.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取当前登录的用户
     * @param session
     * @return
     */
    @RequestMapping("/api/common/currentUser")
    @ResponseBody
    public Response currentUser(HttpSession session) {
        Response resp = new Response();
        User u = (User)session.getAttribute(Constant.USER);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", u.getId());
        map.put("username", u.getUsername());
        map.put("userImg", u.getUserPic());
        resp.setData(map);
        return resp;
    }

    /**
     * 用户登录
     * @param name
     * @param password
     * @return
     */
    @RequestMapping(value = "/api/common/login", method= RequestMethod.POST)
    @ResponseBody
    public Response login(@RequestParam(value = "username", required = true) String name,
                          @RequestParam(value = "password", required = true) String password,
                          HttpSession session) {
        Response resp = new Response();
        String md5Str = null;
        try {
            md5Str = MD5Util.encodeMD5(password);
        } catch ( Exception e) {
            e.printStackTrace();
        }
        User u = userService.getUserByUsernameAndPassword(name, password);
        if( u != null) {
            session.setAttribute(Constant.USER, u);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", u.getId());
            map.put("userPic", u.getUserPic());
            resp.setData(map);
        } else {
            resp.setCode(400);
            resp.setError("用户名或密码错误!");
        }
        return resp;
    }

    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/api/common/register", method= RequestMethod.POST)
    @ResponseBody
    public Response register(@RequestParam(value = "username", required = true) String username,
                             @RequestParam(value = "password", required = true) String password) {
        Response resp = new Response();

        return resp;
    }

}
