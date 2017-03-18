package cn.karent.controller;

import cn.karent.domain.Response;
import cn.karent.entity.Thing;
import cn.karent.service.ThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wan on 2017/3/15.
 * 网站大事件
 */
@Controller
public class ThingController {

    @Autowired
    private ThingService thingService;

    @RequestMapping("/api/common/obtainRecentThing")
    @ResponseBody
    public Response obtainRecentThing() {
        Response resp = new Response();
        List<Thing> list = thingService.findAll();
        List<cn.karent.domain.Thing> list1 = new ArrayList<cn.karent.domain.Thing>();
        for( Thing t : list) {
            cn.karent.domain.Thing t1 = new cn.karent.domain.Thing();
            t1.setTime(t.getCreateTime());
            t1.setThing(t.getDescription());
            list1.add(t1);
        }
        resp.setData(list1);
        return resp;
    }

}
