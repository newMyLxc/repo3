package main;

import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import java.util.Scanner;

public class Demo2 {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    static Scanner sc = new Scanner(System.in);
    static String id;
    static String name;
    static int age;

    public void addstudent() {
        while (true) {//设置一个死循环
            try {
                System.out.println("请输入学生的学号：");
                id = sc.next();
                break;
            } catch (Exception e) {
                System.out.println("输入格式不正确，请重新操作：");
                sc = new Scanner(System.in);
            }
        }

        while (true) {//设置一个死循环
            try {
                System.out.println("请输入学生姓名：");
                name = sc.next();
                break;
            } catch (Exception e) {
                System.out.println("输入格式不正确，请重新操作：");
                sc = new Scanner(System.in);
            }
        }

        while (true) {//设置一个死循环
            try {
                System.out.println("请输入学生年龄：");
                age = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("输入格式不正确，请重新操作：");
                sc = new Scanner(System.in);
            }
        }

            //定义sql语句
            String sql = "insert into student(id, name, age) values(?,?,?)";
            //给？赋值
            int count = template.update(sql, id, name, age);
            System.out.println(count);
            if (count > 0) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");
            }
            new Demo1().system();

        }
    }