package config;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by wsdevotion on 16/1/24.
 */
public final class JdbcConnection {

    private static DataSource myDataSource = null;

    public JdbcConnection() {

    }
    private static String db_driver = "";
    private static String db_url = "";
    private static String db_userName = "";
    private static String db_passWord = "";

    static {
        try {
            Properties pro = new Properties();
            String path = GetProMessage.class.getClassLoader().getResource("").toURI().getPath();
            pro.load(new FileInputStream(path + "/" + "dbcpconfig.properties"));
            myDataSource = BasicDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static DataSource getDataSource() {
        return myDataSource;
    }

    public static Connection getConnection() throws SQLException {
        return myDataSource.getConnection();
    }

    public static void free(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null)
                    try {
                        conn.close();
                        // myDataSource.free(conn);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}
