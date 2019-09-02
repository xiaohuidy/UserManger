package com.user.dao;

import com.user.entity.User;
import com.user.util.JdbcUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public void insertUser(User user){
        //插入数据
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = JdbcUtil.getDBConnection();
            String sql = "insert into users (name,age,birthday,pwd) values(?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, user.getName());
            pst.setInt(2, user.getAge());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            pst.setDate(3, Date.valueOf(sdf.format(user.getBirthday())));
            pst.setString(4, user.getPwd());
            pst.executeUpdate();
            System.out.println("插入成功");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                pst.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public User getUserByName(String name){
        Connection con = null;
        PreparedStatement  pst = null;
        ResultSet rs = null;
        User user = null;
        try {
            con = JdbcUtil.getDBConnection();
            String sql = "select * from users where name = ?";
            pst= con.prepareStatement(sql);
            pst.setString(1, name);
            rs = pst.executeQuery();
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPwd(rs.getString("pwd"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                pst.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public void delUser(int id) {
        //删除数据
        Connection con = null;
        PreparedStatement  pst = null;
        try {
            con = JdbcUtil.getDBConnection();
            String sql = "delete from users where id = ?";
            pst= con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                pst.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public void updateUser(User user) {
        //更新数据
        Connection con = null;
        PreparedStatement  pst =null;
        try {
            con = JdbcUtil.getDBConnection();
            String sql="update users set name = ?, age = ?, birthday = ?,pwd = ? where id = ?";
            pst= con.prepareStatement(sql);
            pst.setString(1, user.getName());
            pst.setInt(2, user.getAge());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            pst.setDate(3, Date.valueOf(sdf.format(user.getBirthday())));
            pst.setString(4, user.getPwd());
            pst.setInt(5, user.getId());
            pst.executeUpdate();
            System.out.println("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                pst.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public List UserSelect(){
        //查询全部数据
        List list = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        try {
            list = new ArrayList();
            conn = JdbcUtil.getDBConnection();
            String sql = "select * from users";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPwd(rs.getString("pwd"));
                list.add(user);
            }
            /*for(Object o:list){
                User s = (User)o;
                System.out.println(s.toString());
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                pst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(list != null)
            System.out.println("查询成功");
        return list;
    }

    public User selUser(int id) {
        //根据ID查数据
        Connection con = null;
        PreparedStatement  pst = null;
        ResultSet rs = null;
        User user = null;
        try {
            con = JdbcUtil.getDBConnection();
            String sql = "select * from users where id = ?";
            pst= con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPwd(rs.getString("pwd"));
                user.setAge(rs.getInt("age"));
                user.setBirthday(rs.getDate("birthday"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                pst.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public List selAge(int ageStart, int ageEnd){
        //根据年龄段查数据
        List list = null;
        Connection con = null;
        PreparedStatement  pst = null;
        ResultSet rs = null;
        User user = null;
        try {
            list = new ArrayList();
            con = JdbcUtil.getDBConnection();
            String sql = "select * from users where age between ? and ? order by age";
            pst = con.prepareStatement(sql);
            pst.setInt(1, ageStart);
            pst.setInt(2, ageEnd);
            rs = pst.executeQuery();
            while(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setBirthday(rs.getDate("birthday"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                pst.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List selLimit(int page,int count){
        //分页查询
        List list = null;
        Connection con = null;
        PreparedStatement  pst = null;
        ResultSet rs = null;
        User user = null;
        try {
            list = new ArrayList();
            con = JdbcUtil.getDBConnection();
            String sql = "select * from users limit ?,?";
            pst= con.prepareStatement(sql);
            pst.setInt(1, (page - 1)*count);
            pst.setInt(2, count);
            rs = pst.executeQuery();
            while(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPwd(rs.getString("pwd"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                pst.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    public int getCounts() {
        Connection conn = null;
        PreparedStatement prep = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getDBConnection();
            String sql = "select count(*)  cc from users";
            prep = conn.prepareStatement(sql);
            rs = prep.executeQuery();
            int counts = 0;
            if(rs.next()) {
                counts = rs.getInt("cc");
            }
            return counts;
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            try {
                rs.close();
                if (conn != null) conn.close();
                if(prep != null) prep.close();
            }catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
