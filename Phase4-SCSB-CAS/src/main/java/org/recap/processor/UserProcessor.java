package org.recap.processor;

import org.recap.model.User;
import org.recap.util.DataSourceUtil;
import org.recap.util.MD5EncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by sheiks on 13/02/17.
 */
@Component
public class UserProcessor {

    @Autowired
    private DataSourceUtil dataSourceUtil;

    public boolean createUser(User user) {
        String sql = "INSERT INTO USERS " +
                "(USERNAME, PASSWORD,EMAIL,FIRSTNAME,LASTNAME) VALUES (?, ?,?, ?, ?)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceUtil.getDataSource());
        int update = jdbcTemplate.update(sql, new Object[]{user.getUsername(),
                MD5EncoderUtil.getMD5EncodingString(user.getPassword()),
                        user.getEmail(), user.getFirstName(), user.getLastName()});
        return (update == 1 ? true : false);

    }

    public boolean updateUser(User user) {
        String sql = "UPDATE USERS SET PASSWORD = '" + MD5EncoderUtil.getMD5EncodingString(user.getPassword()) +
                "' WHERE USERNAME = '" + user.getUsername() + "' AND EMAIL = '" + user.getEmail() + "'";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceUtil.getDataSource());
        int update = jdbcTemplate.update(sql);
        return (update == 1 ? true : false);

    }
}
