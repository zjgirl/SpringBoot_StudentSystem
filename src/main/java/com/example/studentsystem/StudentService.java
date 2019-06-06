//service 接口类，定义需求方法

package com.example.studentsystem;

import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    Student save(Student student);
    Student update(Student student);
    void delete(Integer id);
    Student findStuById(Integer id);
    List<Student> findStuByName(String name);

    Page<Student> findAll(int page, int pageSize);
}
