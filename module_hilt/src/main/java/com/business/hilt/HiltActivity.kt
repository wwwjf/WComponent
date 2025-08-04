package com.business.hilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.base.router.constant.ARouterPath
import com.common.utils.ToastUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@Route(path = ARouterPath.MODULE_HILT_ACTIVITY)
@AndroidEntryPoint
class HiltActivity : AppCompatActivity() {

    @Inject
    lateinit var truck: Truck

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hilt)
        val result = truck.drive()
        if (result) ToastUtil.showCenter(this, "drive")

    }
}