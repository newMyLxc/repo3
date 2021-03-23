package cn.itcast.jdbc;

import java.util.ArrayList;
import java.util.List;

public class JdbcDemo9 {
    public static void main(String[] args) {
        List<emp> list = new ArrayList<emp>();
        emp p1 = new emp();
        emp p2 = new emp();
        p1.setEname("111");
        p1.setId(1);
        p2.setEname("222");
        p2.setId(2);
        list.add(p1);
        list.add(p2);
        for(emp p : list){
            System.out.println(p);
        }
    }
}

class emp{
    private int id;
    private String ename;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    @Override
    public String toString(){
        return "id=" + id + "ename=" +  ename;
    }
}
