package com.example.studentsystem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //当JPA检测到我们的实体类当中有@Entity注解的时候，会在数据库中生成对应的表结构信息
@Table(name = "class")
public class Class {
    @Id
    @GeneratedValue
    private int classid;

    public int getClassid() {
        return classid;
    }

    public void setClassid(int id){
        classid = id;
    }
}
