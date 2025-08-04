package com.business.hilt

import com.common.log.KLog
import javax.inject.Inject

class Truck @Inject constructor() {
    
    fun drive(): Boolean {
        KLog.e("======Driving a truck====")
        return true
    }
}