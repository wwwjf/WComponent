package com.common.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.common.protobuf.proto.Setting.Student
import kotlinx.coroutines.flow.first


val Context.studentDataStore: DataStore<Student> by dataStore(
    fileName = "student.pb",
    serializer = StudentSerializer
)

class DatastoreProtobufManager(context: Context) {

    val studentDataStore = context.studentDataStore

    suspend fun saveStudent(student: Student) {
        studentDataStore.updateData { stu ->
            stu.toBuilder()
                .setName(student.name)
                .setAge(student.age)
                .build()
        }
    }

    suspend fun getStudent(): Student {
        return studentDataStore.data.first()
    }

}