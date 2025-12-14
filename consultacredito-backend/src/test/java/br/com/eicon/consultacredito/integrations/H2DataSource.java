package br.com.eicon.consultacredito.integrations;

import javax.sql.DataSource;
import org.h2.jdbcx.JdbcDataSource;

public class H2DataSource {

    private static DataSource instance;

    private H2DataSource() {
        try {
            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL("jdbc:h2:mem:creditodb");
            ds.setUser("user");
            ds.setPassword("user@123");

            instance = ds;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getInstance() {
        if (instance == null) {
            synchronized (H2DataSource.class) {
                if (instance == null) {
                    new H2DataSource();
                }
            }
        }
        return instance;
    }
}
