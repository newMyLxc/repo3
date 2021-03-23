package cn.itcast.jdbctemplate;

import cn.itcast.domain.Emp;
import cn.itcast.utils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 			* 需求：
 * 				1. 修改1号数据的 salary 为 10000
 * 				2. 添加一条记录
 * 				3. 删除刚才添加的记录
 * 			   对应db3中emp表
 */
public class jdbcTemplateDemo2 {
    //Junit单元测试，可以让方法独立执行

    //不用单独的在每个test中写
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 修改1号数据的 salary 为 10000
     */
    @Test
    public void test1(){
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "update emp set salary = 10000 where id = 1001";
        int count = template.update(sql);
        System.out.println(count);
    }

    /**
     * 添加一条记录
     */
    @Test
    public void test2(){
        String sql =  "insert into emp(id,ename,dept_id) values(?,?,?)";
        int count = template.update(sql,1015,"郭靖",10);
        System.out.println(count);
    }

    /**
     * 删除刚才添加的记录
     */
    @Test
    public void test3(){
        String sql =  "delete from emp where id = ?";
        int count = template.update(sql,1015);
        System.out.println(count);
    }

    /**
     * 查询id为1的记录，将其封装为Map集合
     * 注意，这个查询的结果集长度只能是1
     */
    @Test
    public void test4(){
        String sql = "select * from emp where id = ?";
        Map<String, Object> map = template.queryForMap(sql, 1001);
        System.out.println(map);
    }

    /**
     * 查询所有记录，将其封装为List
     */
    @Test
    public void test5(){
        String sql = "select * from emp";
        List<Map<String, Object>> list = template.queryForList(sql);
        for (Map<String, Object> stringObjectMap : list) {//iter快捷遍历语句
            System.out.println(stringObjectMap);
        }
    }

    /**
     * 查询所有记录，将其封装为Emp对象的List集合
     */
    @Test
    public void test6(){
        String sql = "select * from emp";
        List<Emp> list = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
        for (Emp emp : list) {
            System.out.println(emp);
        }
        /*template.query(sql, new RowMapper<Emp>() {
            @Override
            public Emp mapRow(ResultSet rs, int i) throws SQLException {
                Emp emp = new Emp();
                int id = rs.getInt("id");
                String ename = rs.getString("ename");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joindate = rs.getDate("joindate");
                double salary = rs.getDouble("salary");
                double bonus = rs.getDouble("bonus");
                int dept_id = rs.getInt("dept_id");

                emp.setId(id);
                ...


                return null;
            }
        });*/
    }

    /**
     * 查询总记录数
     */
    @Test
    public void test7(){
        String sql = "select count(id) from emp";
        Long total = template.queryForObject(sql, Long.class);
        System.out.println(total);
    }
}
