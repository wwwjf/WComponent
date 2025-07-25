package com.base.mvvm_two.utils

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.common.log.KLog
import com.google.gson.Gson
import java.lang.reflect.Type

object ApiDataCacheUtil {
    var CACHENAME = "SPUtils-android"

    val dataMap = HashMap<String, Any>()

    private fun putString(
        context: Context,
        key: String,
        value: String
    ) {
        Log.i("SPUtils", "put key:$key  value:$value")
        val sp =
            context.getSharedPreferences(CACHENAME, Context.MODE_PRIVATE)
        val edit = sp.edit()
        edit.putString(key, value)
        edit.commit()
    }

    private fun getString(context: Context, key: String): String? {
        val sp =
            context.getSharedPreferences(CACHENAME, Context.MODE_PRIVATE)
        val result = sp.getString(key, "")
        Log.i("SPUtils", "get key:$key  value:$result")
        return result
    }

    fun <T> getCache(
        key: String,
        clazz: Class<T>?
    ): T? {

        var res = dataMap[key]
        if (res != null) {
            Log.i("MyLog", "使用内存缓存")
            return res as T
        }

        res = ContextUtil.context?.let { getObject(it, key, clazz) }
        if (res != null) {
            Log.i("MyLog", "使用文件缓存")
            dataMap[key] = res
        }
        return res
    }


    fun putCache(
        key: String?,
        bean: Any?
    ) {
        if (ContextUtil.context == null || bean == null || key == null) {
            KLog.e("=====context=${ContextUtil.context}==bean=${bean}==key=${key}")
            return
        }
        dataMap[key] = bean
        Log.i("MyLog", "更新数据到内存缓存")
        if (dataMap.size > 100)
            dataMap.clear()
        putString(ContextUtil.context!!, key, Gson().toJson(bean))
        Log.i("MyLog", "更新数据到文件缓存")
    }


    private fun <T> getObject(
        context: Context,
        key: String,
        clazz: Class<T>?
    ): T? {
        if (context == null)
            return null
        val json = getString(context, key)
        return if (TextUtils.isEmpty(json)) {
            null
        } else try {
            Gson().fromJson<T>(json, clazz)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun <T> getObjects(
        context: Context,
        key: String,
        type: Type?
    ): ArrayList<T>? {
        val json = getString(context, key)
        return if (TextUtils.isEmpty(json)) {
            null
        } else try {
            Gson().fromJson<ArrayList<T>>(json, type)
        } catch (e: Exception) {
            null
        }
    }
}