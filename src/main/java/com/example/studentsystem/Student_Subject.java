package com.example.studentsystem;

import javax.persistence.*;

//存储多对多关系的这张表会自动生成，不用我们定义
@Entity
@Table(name = "student_subject")
public class Student_Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int student_id;
    private int subject_id;
}
