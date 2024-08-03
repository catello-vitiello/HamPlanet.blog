package utils;

import org.apache.commons.dbcp2.BasicDataSource;;

public class MockDataSource {

    public static BasicDataSource createDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.1.9:3306/freedb_hpblog_db");
        dataSource.setUsername("HPBlog.admin");
        dataSource.setPassword("admin-1234");
        dataSource.setDefaultAutoCommit(false);
        dataSource.setAutoCommitOnReturn(false);
        dataSource.setRollbackOnReturn(true);
        return dataSource;
    }

}
