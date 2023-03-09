package com.se.uok.crud_grpc.rpc;

import com.example.grpcdemo.GetStudentReq;
import com.example.grpcdemo.MyServiceGrpc;
import com.example.grpcdemo.Student;
import com.example.grpcdemo.StudentsList;
import com.example.grpcdemo.SuccessResponse;
import com.example.grpcdemo.deleteStudent;
import com.google.protobuf.Empty;
import com.se.uok.crud_grpc.service.CrudService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
@Slf4j
@RequiredArgsConstructor
public class CrudRpcServiceImpl extends MyServiceGrpc.MyServiceImplBase {

    @Autowired
    private final CrudService crudService;

    @Override
    public void getStudent(GetStudentReq request, StreamObserver<Student> responseObserver) {
        Student studentRes = crudService.getStudent((int) request.getId());
        responseObserver.onNext(studentRes);
        responseObserver.onCompleted();
    }

    @Override
    public void getStudentDetails(Empty request, StreamObserver<StudentsList> responseObserver) {
        StudentsList studentsList = crudService.getStudentList();
        responseObserver.onNext(studentsList);
        responseObserver.onCompleted();
    }

    @Override
    public void saveStudentDetails(Student request, StreamObserver<SuccessResponse> responseObserver) {
        SuccessResponse successResponse = crudService.saveStudent(request);
        responseObserver.onNext(successResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void updateStudentDetails(Student request, StreamObserver<SuccessResponse> responseObserver) {
        SuccessResponse successResponse = crudService.updateStudent(request);
        responseObserver.onNext(successResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteStudentDetails(deleteStudent request, StreamObserver<SuccessResponse> responseObserver) {
        SuccessResponse successResponse = crudService.deleteStudent((int) request.getId());
        responseObserver.onNext(successResponse);
        responseObserver.onCompleted();
    }
}
