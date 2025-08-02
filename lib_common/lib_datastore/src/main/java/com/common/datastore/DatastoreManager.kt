package com.common.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.common.protobuf.ProtobufUtil
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map


val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "ds_manager")


class DatastoreManager(context: Context) {


    val datastore = context.datastore


    companion object {
        val key_name = stringPreferencesKey("key_name")
    }

    suspend fun saveName(name: String) {
        datastore.edit {
            it[key_name] = name
        }


    }

    suspend fun getName(): String? {
        val name = datastore.data.map {
            it[key_name]
        }.firstOrNull()
        return name
    }

    suspend fun clearName() {
        datastore.edit {
            it.clear()
        }
    }

    suspend fun saveKey(key: String, value: String) {
        datastore.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    suspend fun getKey(key: String): String? {
        val name = datastore.data.map {
            it[stringPreferencesKey(key)]
        }.firstOrNull()
        return name
    }
}