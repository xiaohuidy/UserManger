package com.user.service;

import com.user.dao.UserDao;
import com.user.entity.User;

import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();
    public void save(User user){
        userDao.insertUser(user);
    }
    public List getAll(){
        return userDao.UserSelect();
    }
    public boolean canLogin(String name,String pwd){
        User user = null;
        user = userDao.getUserByName(name);
       if(user!=null && user.getPwd().equals(pwd)){
           return true;
       }
       return false;
    }

    public User getOne(String name,String pwd){
        User user = null;
        user = userDao.getUserByName(name);
        if(user!=null && user.getPwd().equals(pwd)){
            return user;
        }
        return null;
    }

    public User sel(int id){

        return userDao.selUser(id);
    }

    public List del(int id){
        userDao.delUser(id);
        return userDao.UserSelect();
    }

    public List update(User user){
        userDao.updateUser(user);
        return userDao.UserSelect();
    }

    public List getPagedUsers(int page,int size) {
        //return userDao.getPagedUsers(page,size);
        return  userDao.selLimit(page,size);
    }

    public int getCounts() {
        return userDao.getCounts();
    }

    public boolean isSaved(String name){
        return userDao.getUserByName(name)!=null?true:false;
    }
}
