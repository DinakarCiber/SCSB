package org.recap.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/**
 * Created by sheiks on 13/02/17.
 */
@Component
public class DataSourceUtil {
    private static DataSourceUtil ourInstance = new DataSourceUtil();
    private DriverManagerDataSource dataSource;

    @Value("${cas.authn.jdbc.query[0].url}")
    private String url;

    @Value("${cas.authn.jdbc.query[0].user}")
    private String username;

    @Value("${cas.authn.jdbc.query[0].password}")
    private String password;

    public DriverManagerDataSource getDataSource() {
        if(null == dataSource) {
            dataSource = new DriverManagerDataSource(url,username,password);;
        }
        return dataSource;
    }

}
