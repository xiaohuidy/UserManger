package com.user.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private int id;
    private String name;
    private int age;
    private Date birthday;
    private String pwd;

    public User(){

    }

    public User(String name,int age,Date birthday){
        this.age = age;
        this.birthday = birthday;
        this.name = name;
    }
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "User{" +
                "id=" + id +
                ",password='"+pwd+'\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + sdf.format(birthday) +
                '}';
    }
}
