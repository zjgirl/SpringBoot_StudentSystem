package com.example.studentsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class StudentController {
    //Autoweired自动导入其他类中的方法
    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public Student add(@RequestBody Student student){ //测试时需要传入String类型，这里需要用@RequestBody转一下

        return studentService.save(student);
    }

    @PutMapping("/student")
    public Student update(Student student){

        return studentService.save(student);
    }

    @GetMapping("/student/{id}")
    public Student findStuById(@PathVariable Integer id){
        return studentService.findStuById(id);
    }

    @GetMapping("/student-name/{name}")
    public List<Student> findStuByName(@PathVariable String name){
        return studentService.findStuByName(name);
    }

    @GetMapping("/hello")
    public String findStuByName(){
        return "Hello World.";
    }

    @DeleteMapping("/student/{id}")
    public String delete(@PathVariable Integer id){ //@PathVariable可以进行类型转换（URL中传入的id是String类型的，转成Integer）
        studentService.delete(id);
        return "删除成功！";
    }

    @GetMapping("/student-page/{page}")
    public Page<Student> findByPage(@PathVariable Integer page, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*"); //解决跨域请求
        if( page==null || page<0){
            page = 0;
        } else {
            page -= 1;
        }
        return studentService.findAll(page, 5);
    }
}
