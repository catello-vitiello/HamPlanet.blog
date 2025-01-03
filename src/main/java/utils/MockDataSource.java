package utils;

import org.apache.commons.dbcp2.BasicDataSource;;

public class MockDataSource {

    public static BasicDataSource createDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/db_test_is");
        dataSource.setUsername("is_test");
        dataSource.setPassword("is_test");
        dataSource.setDefaultAutoCommit(false);
        dataSource.setAutoCommitOnReturn(false);
        dataSource.setRollbackOnReturn(true);
        return dataSource;
    }


}
