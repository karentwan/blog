package cn.karent.controller;

import cn.karent.domain.Response;
import cn.karent.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wan on 2017/3/14.
 */
@Controller
public class MusicController {

    @Autowired
    private MusicService musicService;

    @RequestMapping("/api/common/getRandomMusic")
    @ResponseBody
    public Response getRandomMusic() {
        Response resp = new Response();
        String path = musicService.getRandomMusic();
        resp.setData(path);
        return resp;
    }

}
