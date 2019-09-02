package com.user.dao;

import com.user.entity.User;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class UserDaoTest {

    @Test
    public void insertUser() throws Exception {
        UserDao userDao = new UserDao();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        User user = new User("Wang",10,sdf.parse("2019-04-08"));
        userDao.insertUser(user);
    }

    @Test
    public void delUser(){
        UserDao userDao = new UserDao();
        userDao.delUser(1);
    }

    @Test
    public void updateUser() throws ParseException {
        UserDao userDao = new UserDao();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        User user = new User("Wang",20,sdf.parse("2019-05-08"));
        userDao.updateUser(user);
    }

    @Test
    public void UserSelect() {
        UserDao userDao = new UserDao();
        List list = userDao.UserSelect();
        for (Object o : list) {
            User s = (User) o;
            System.out.println(s.toString());
        }
    }

    @Test
    public void selUser() {
        UserDao userDao = new UserDao();
        User user = userDao.selUser(7);
        System.out.println(user.toString());
    }

    @Test
    public void selAge(){
        UserDao userDao = new UserDao();
        List list = userDao.selAge(19,21);
        //System.out.println(list.size());
        for (Object o : list) {
            User s = (User) o;
            System.out.println(s.toString());
        }
    }

    @Test
    public void selLimit(){
        UserDao userDao = new UserDao();
        List list = userDao.selLimit(1,2);
        for (Object o : list) {
            User s = (User) o;
            System.out.println(s.toString());
        }
    }

    @Test
    public void getUserByName(){
        UserDao userDao = new UserDao();
        User user = userDao.getUserByName("老王");
        System.out.println(user.toString());
    }
}
