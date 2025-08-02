package com.common.protobuf;


import com.common.protobuf.proto.Setting.Student;

public class ProtobufUtil {

    public static Student getStudent() {
        Student student = Student.newBuilder()
                .setName("张三")
                .build();
        return student;
    }
}
