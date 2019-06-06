//Mapper层（Dao层）：数据持久化，封装对数据库进行数据持久化操作（也就是一些对数据库的操作方法）

package com.example.studentsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentMapper extends JpaRepository<Student, Integer> { //JpaRepository中有一些常用的查找删除的方法

    //新增加的方法
    List<Student> findAllByName(@Param("name") String name); //当传入的参数有多个时，要用@Param
}
