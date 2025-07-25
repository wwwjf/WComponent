package com.base.mvvm_two

import com.common.eventbus.event.MessageEvent
import com.google.gson.GsonBuilder
import org.greenrobot.eventbus.EventBus

class BeanLog {
    var tag=""
    var url = ""
    var json = ""

    fun send(tag:String,path:String,json:Any){
        this.tag = if(tag==""){
            "接口：${path.split("?")[0].split("/").last()}"
        }else{
            "接口：$tag"
        }
        this.url=path
        this.json= GsonBuilder().setPrettyPrinting()  .create().toJson(json)
        EventBus.getDefault().post(MessageEvent(31415928, this))
    }
}