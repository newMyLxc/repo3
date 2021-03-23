package cn.itcast.jdbc;

import cn.itcast.util.JdbcUtils;

import java.sql.*;

public class JdbcDemo11事务 {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        try {
            //1.获取连接
            conn = JdbcUtils.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            //2.定义sql
            //2.1张三 - 500
            String sql1 = "update account set balance = balance - ? where id = ?";
            //2.2李四 +500
            String sql2 = "update account set balance = balance + ? where id = ?";
            //3.获取执行sql对象
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            //4.设置参数
            pstmt1.setDouble(1,500);
            pstmt1.setInt(2,1);
            pstmt2.setDouble(1,500);
            pstmt2.setInt(2,2);
            //5.执行sql
            pstmt1.executeUpdate();

            pstmt2.executeUpdate();
            //提交事务
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            //事务回滚
            try {
                if(conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally{
            JdbcUtils.close(pstmt1, conn);
            JdbcUtils.close(pstmt2, null);
        }
    }

}
