package utils;

import org.apache.commons.dbcp2.BasicDataSource;;

public class MockDataSource {

    public static BasicDataSource createDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/freedb_HPBlog_db");
        dataSource.setUsername("root");
        dataSource.setPassword("Niky-1904");
        dataSource.setDefaultAutoCommit(false);
        dataSource.setAutoCommitOnReturn(false);
        dataSource.setRollbackOnReturn(true);
        return dataSource;
    }

}
