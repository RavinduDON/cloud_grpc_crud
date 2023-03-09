package com.se.uok.crud_grpc.service.impl;

import com.example.grpcdemo.Student;
import com.example.grpcdemo.StudentsList;
import com.example.grpcdemo.SuccessResponse;
import com.se.uok.crud_grpc.repository.StudentRepository;
import com.se.uok.crud_grpc.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@GRpcService
@RequiredArgsConstructor
public class CrudServiceImpl implements CrudService {

    @Autowired
    private final StudentRepository studentRepository;


    @Override
    public Student getStudent(int id) {
        Optional<com.se.uok.crud_grpc.model.Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return Student.newBuilder()
                    .setId(student.get().getId())
                    .setName(student.get().getName())
                    .setAddress(student.get().getAddress())
                    .setAge(student.get().getAge())
                    .setYear(student.get().getYear())
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public StudentsList getStudentList() {
        List<com.se.uok.crud_grpc.model.Student> studentList = studentRepository.findAll();
        List<Student> studentsDTOList = new ArrayList<>();
        for (com.se.uok.crud_grpc.model.Student student : studentList) {
            Student studReq = Student.newBuilder().setId(student.getId()).setName(student.getName()).setAge(student.getAge())
                    .setAddress(student.getAddress()).setYear(student.getYear()).build();
            studentsDTOList.add(studReq);
        }
        return StudentsList.newBuilder().addAllStudentList(studentsDTOList).build();
    }

    @Override
    public SuccessResponse saveStudent(Student student) {
        com.se.uok.crud_grpc.model.Student studentDto = new com.se.uok.crud_grpc.model.Student();
        studentDto.setName(student.getName());
        studentDto.setAddress(student.getAddress());
        studentDto.setAddress(student.getAddress());
        studentDto.setAge((int) student.getAge());
        studentDto.setYear((int) student.getYear());
        com.se.uok.crud_grpc.model.Student studentDTO = studentRepository.save(studentDto);
        return studentDTO != null ? SuccessResponse.newBuilder().setMessage("Success").build() :
                SuccessResponse.newBuilder().setMessage("Failed").build();
    }

    @Override
    public SuccessResponse updateStudent(Student student) {
        Optional<com.se.uok.crud_grpc.model.Student> getStd = studentRepository.findById((int)student.getId());
        if (getStd.isPresent()) {
            com.se.uok.crud_grpc.model.Student studentDTO = getStd.get();
            studentDTO.setYear((int)student.getYear());
            studentDTO.setAge((int)student.getAge());
            studentDTO.setAddress(student.getAddress());
            studentRepository.save(studentDTO);
            return SuccessResponse.newBuilder().setMessage("Success").build();
        }
        return SuccessResponse.newBuilder().setMessage("Failed").build();
    }

    @Override
    public SuccessResponse deleteStudent(int id) {
        studentRepository.deleteById(id);
        return SuccessResponse.newBuilder().setMessage("Success").build();
    }
}
