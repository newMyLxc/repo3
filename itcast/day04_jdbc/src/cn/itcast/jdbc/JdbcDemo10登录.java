package cn.itcast.jdbc;

import cn.itcast.util.JdbcUtils;

import java.sql.*;
import java.util.Scanner;

public class JdbcDemo10登录 {
    public static void main(String[] args) {
        //1.键盘录入
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.next();
        System.out.println("请输入密码：");
        String password = sc.next();
        //2.调用方法
        if(new JdbcDemo10登录().login(username, password)){
            System.out.println("登录成功");
        }else{
            System.out.println("用户名或密码错误，登录失败");
        }
        //3.判断结果，输出不同语句
    }

    /**
     * 登录方法，使用Statement实现
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username, String password){
        if(username.equals(null) || password.equals(null)){
            return false;
        }
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //1.获取连接
            conn = JdbcUtils.getConnection();
            //2.定义sql
            String sql = "select * from user where username = '"+ username +"' and password = '"+ password +"' ";
            //3.获取执行sql对象
            stmt = conn.createStatement();
            //4.执行查询
            rs = stmt.executeQuery(sql);
            //5.判断
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.close(rs,stmt,conn);
        }

        return false;
    }

    /**
     * 登录方法，使用PrepareStatement实现
     * @param username
     * @param password
     * @return
     */
    public boolean login2(String username, String password){
        if(username.equals(null) || password.equals(null)){
            return false;
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //1.获取连接
            conn = JdbcUtils.getConnection();
            //2.定义sql
            String sql = "select * from user where username = ? and password = ?";
            //3.获取执行sql对象
            pstmt = conn.prepareStatement(sql);
            //给?赋值
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            //4.执行查询
            rs = pstmt.executeQuery();
            //5.判断
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.close(rs,pstmt,conn);
        }

        return false;
    }
}
