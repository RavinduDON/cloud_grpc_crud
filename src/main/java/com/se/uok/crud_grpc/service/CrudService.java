package com.se.uok.crud_grpc.service;

import com.example.grpcdemo.Student;
import com.example.grpcdemo.StudentsList;
import com.example.grpcdemo.SuccessResponse;

import java.util.List;

public interface CrudService {

    Student getStudent(int id);
    StudentsList getStudentList();
    SuccessResponse saveStudent(Student student);
    SuccessResponse updateStudent(Student student);
    SuccessResponse deleteStudent(int id);
}
