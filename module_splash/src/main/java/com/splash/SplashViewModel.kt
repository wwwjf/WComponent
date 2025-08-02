package com.splash

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.business.utils.Utils
import com.common.datastore.DatastoreManager
import com.common.datastore.DatastoreProtobufManager
import com.common.log.KLog
import com.common.protobuf.proto.Setting.Student
import com.common.utils.TimeUtils
import com.common.utils.ToastUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Locale


open class SplashViewModel() : ViewModel() {

    var title: ObservableField<String> = ObservableField("")
    private val datastoreManager = DatastoreManager(Utils.getApp())
    private val datastoreProtobufManager = DatastoreProtobufManager(Utils.getApp())

    init {
        title.set("跳转到Mine")
    }

    fun init() {
        viewModelScope.launch(Dispatchers.Main){
            flow { emit(1) }.flowOn(Dispatchers.IO).collect {

            }
        }
    }
    fun saveDataStoreFile() {
        viewModelScope.launch {
            val millis2Date = TimeUtils.millis2String(System.currentTimeMillis())
            datastoreManager.saveName("SplashViewModel $millis2Date")
            datastoreManager.saveKey("userId", "12345678")
            datastoreManager.saveKey("loginTime", millis2Date)
        }
    }

    fun getDataStoreFile() {
        viewModelScope.launch {
            val content = datastoreManager.getName() + "_" +
                    datastoreManager.getKey("userId") + "_" +
                    datastoreManager.getKey("loginTime")
            KLog.e("=====dataStoreFile: $content")
            ToastUtil.show(Utils.getApp(), content)
        }
    }

    fun saveDataStoreProtobufFile() {
        viewModelScope.launch {
            val millis2Date = TimeUtils.millis2String(System.currentTimeMillis())
            val student = Student.newBuilder()
                .setName("SplashViewModel $millis2Date")
                .setAge(18)
                .build()
            datastoreProtobufManager.saveStudent(student)
        }
    }

    fun getDataStoreProtobufFile() {
        viewModelScope.launch {
            val student = datastoreProtobufManager.getStudent()
            KLog.e("=====dataStoreProtobufFile: $student")
            ToastUtil.show(Utils.getApp(), student.name)
        }
    }

}