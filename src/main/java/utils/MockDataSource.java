package utils;

import org.apache.commons.dbcp2.BasicDataSource;;

public class MockDataSource {

    public static BasicDataSource createDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://sql.freedb.tech:3306/freedb_HPBlog_db");
        dataSource.setUsername("freedb_user_");
        dataSource.setPassword("zf&P@Euaz4cn$yS");
        dataSource.setDefaultAutoCommit(false);
        dataSource.setAutoCommitOnReturn(false);
        dataSource.setRollbackOnReturn(true);
        return dataSource;
    }

}
