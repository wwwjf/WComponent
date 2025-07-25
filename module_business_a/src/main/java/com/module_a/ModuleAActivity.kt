package com.module_a

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.base.mvvm_two.cache
import com.base.mvvm_two.error
import com.base.mvvm_two.initVM
import com.base.mvvm_two.obs
import com.base.mvvm_two.success
import com.base.router.constant.ARouterPath
import com.common.log.KLog
import com.common.utils.ToastUtil
import com.module_a.databinding.ActivityModuleAactivityBinding


@Route(path = ARouterPath.MODULE_A_ACTIVITY)
class ModuleAActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_module_aactivity)
        val binding = DataBindingUtil.setContentView<ActivityModuleAactivityBinding>(
            this,
            R.layout.activity_module_aactivity
        )
        val viewModel = initVM(ModuleAViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val data = viewModel.checkVersion()
        data.obs(this) {
            it.success {
                KLog.e("module_A_Activity===success===数据是否来自本地缓存=${it.cache},${it.data}")
                viewModel.versionInfo.value = it.data
            }
            it.error { ToastUtil.show(this,it.errmsg) }
            it.cache {
                KLog.e("module_A_Activity===cache===数据是否来自本地缓存=${it.cache},${it.data}")
//                viewModel.versionInfo.value = it.data
            }
        }
    }
}