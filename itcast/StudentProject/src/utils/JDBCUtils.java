package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Druid连接池工具
 */
public class JDBCUtils {

    //定义成员变量DataSource接口获取数据库Connection对象
    private static DataSource ds;

    static {

        try {
            //加载配置文件
            Properties pro = new Properties();
            //getResourceAsStream返回一个InputStream对象方便对此文件资源通过IO流进行获取。
            //pro.load加载文件（）
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            //获取datasource
            ds = DruidDataSourceFactory.createDataSource(pro);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 释放资源
     */
    public static void close(Statement stmt, Connection conn){
        close(null, stmt, conn);
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if(stmt != null){
            try{
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(rs != null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 获取连接池方法
     */
    public static  DataSource getDataSource(){
        return ds;
    }
}

