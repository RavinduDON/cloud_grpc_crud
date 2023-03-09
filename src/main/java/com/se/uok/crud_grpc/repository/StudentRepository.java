package com.se.uok.crud_grpc.repository;

import com.se.uok.crud_grpc.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
