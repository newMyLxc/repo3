package main;

import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Demo1 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loginmain();
        system();
    }

    public static void system(){
        System.out.println("1.添加一个学生");
        System.out.println("2.查找一个学生");
        System.out.println("3.根据学生编号更新学生基本信息");
        System.out.println("4.根据学生编号删除学生");
        System.out.println("5.根据学生编号删除学生");
        System.out.println("6.根据学生编号输入学生各门成绩");
        System.out.println("7.根据某门成绩进行排序");
        System.out.println("8.根据总分进行排序");
        System.out.println("99.退出系统");
        int opreate = sc.nextInt();
        switch (opreate){
            case 1 : new Demo2().addstudent();
        }
    }

    /**
     * 登录
     */
    public static void loginmain(){
        System.out.println("学生管理系统");
        //键盘录入
        System.out.println("请输入用户名：");
        //获取用户名
        String username = sc.next();
        //获取密码
        System.out.println("请输入密码：");
        String password = sc.next();
        //调用login方法进行判断
        if(new Demo1().login(username, password)){
            System.out.println("登录成功");
            System.out.println("欢迎进入学生管理系统！");
        }else{
            System.out.println("用户名或密码错误，登录失败");
            loginmain();
        }
    }

    /**
     * 登录方法，使用PrepareStatement实现
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username, String password){
        if(username.equals(null) || password.equals(null)){
            return false;
        }
        //2.创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        //定义sql
        String sql = "select * from user where username = ? and password = ?";
        //?赋值
        //Map<String, Object> map = template.queryForMap(sql, username, password);千万不要使用map集合无符合数据会异常
        //使用list集合可以返回空的
        List<Map<String, Object>> list = template.queryForList(sql,username,password);
        //判断map集合是否有符合的数据
        return !list.isEmpty() ;

    }
}
