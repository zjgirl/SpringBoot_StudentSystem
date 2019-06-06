//Model层，student实体类

package com.example.studentsystem;

import javax.persistence.*;
import java.util.List;

@Entity //当JPA检测到我们的实体类当中有@Entity注解的时候，会在数据库中生成对应的表结构信息
@Table(name = "student")
public class Student {
    @Id //@Id 标注用于声明一个实体类的属性映射为数据库的主键列
    @GeneratedValue(strategy = GenerationType.IDENTITY) //生成主键，并自增
    private Integer id;
    private String name;
    private int age;

    @ManyToOne(cascade = CascadeType.MERGE) //CascadeType.MEGE级联更新操作
    @JoinColumn(name = "classid", referencedColumnName = "classid") //name是当前表中的字段(会在数据表中生成一个classid字段)，refere是外键
    private Class classInfo;


    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER) //fetch采用EAGER全部抓取
    // 其实可以不用使用@JoinTable，默认生成的关联表名称为主表表名+下划线+从表表名，即student_subject
    // 且关联的外键也是默认生成的，关联到主表的外键名：主表名+下划线+主表中的主键列名,即student_id
    // 同样，关联到从表的外键名：主表中用于关联的属性名+下划线+从表的主键列名,即subject_id
    // 所以，student_id就自动关联的是student表的id字段
    @JoinTable(name = "student_subject", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> subjectInfo; //因为是多对多，所以这里一定要返回一个List

    //需要定义这些方法才能获取到数据库字段
    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public int getClassInfo(){
        return classInfo.getClassid();
    }

    public void setClassInfo(int id){
        classInfo.setClassid(id);
    }

}
