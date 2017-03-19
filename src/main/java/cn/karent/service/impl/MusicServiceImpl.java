package cn.karent.service.impl;

import cn.karent.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wan on 2017/3/14.
 */
@Service
@Transactional
public class MusicServiceImpl implements MusicService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getRandomMusic() {
        String sql = "select path from bk_music order by rand() limit 1";
        final StringBuffer sb = new StringBuffer();
        jdbcTemplate.query(sql, new Object[]{}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                sb.append(resultSet.getString(1));
            }
        });
        return sb.toString();
    }

}
