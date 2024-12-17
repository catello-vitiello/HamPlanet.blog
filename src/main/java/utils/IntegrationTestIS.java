package utils;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class IntegrationTestIS {

    public static DataSource getConnection() throws Exception {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/db_test_IS");
        dataSource.setUsername("is_test");
        dataSource.setPassword("Is_db_test");
        return  dataSource;
    }
}
