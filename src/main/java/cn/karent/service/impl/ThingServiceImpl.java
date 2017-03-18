package cn.karent.service.impl;

import cn.karent.dao.ThingDao;
import cn.karent.entity.Thing;
import cn.karent.service.ThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by wan on 2017/3/15.
 */
@Service
@Transactional
public class ThingServiceImpl implements ThingService {

    @Autowired
    private ThingDao thingDao;

    @Override
    public List<Thing> findAll() {
        return thingDao.findAll();
    }
}
