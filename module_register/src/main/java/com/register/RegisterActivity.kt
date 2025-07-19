package com.register

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.base.router.BaseRouterActivity
import com.base.router.constant.ARouterPath
import com.common.utils.ToastUtil
import com.register.databinding.ActivityRegisterBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Route(path = ARouterPath.REGISTER_ACTIVITY)
class RegisterActivity : BaseRouterActivity() {
    private val registerViewModel by viewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityRegisterBinding>(this, R.layout.activity_register)
        binding.viewModel = registerViewModel
        binding.lifecycleOwner = this
        registerViewModel.checkVersion()
    }

}