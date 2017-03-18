package cn.karent.service;

import cn.karent.entity.User;

/**
 * Created by wan on 2017/3/12.
 */
public interface UserService {

    User getOne(Long userId);

    User getUserByUsernameAndPassword(String username, String password);

}
