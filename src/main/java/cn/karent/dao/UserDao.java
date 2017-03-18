package cn.karent.dao;

import cn.karent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by wan on 2017/3/12.
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User getUserByUsernameAndPassword(@Param("username") String name, @Param("password") String password);

}
