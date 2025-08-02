package com.common.datastore

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.common.protobuf.proto.Setting.Student
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream


object StudentSerializer: Serializer<Student> {
    override val defaultValue: Student
        get() = Student.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Student {
        try {
            return Student.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", e)
        }
    }

    override suspend fun writeTo(
        t: Student,
        output: OutputStream
    ) {
        t.writeTo(output)
    }
}