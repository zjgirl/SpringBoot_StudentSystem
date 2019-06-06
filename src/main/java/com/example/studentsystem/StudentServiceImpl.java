//service实现类

package com.example.studentsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student save(Student student){
        return studentMapper.save(student);
    }

    @Override
    public Student update(Student student){
        return studentMapper.save(student);
    }

    @Override
    public void delete(Integer id){
        studentMapper.deleteById(id);
    }

    @Override
    public Student findStuById(Integer id){
        return studentMapper.findById(id).get();
    }

    @Override
    public List<Student> findStuByName(String name){
        return studentMapper.findAllByName(name);
    }

    @Override
    public Page<Student> findAll(int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);
        return studentMapper.findAll(pageable);
    }
}
