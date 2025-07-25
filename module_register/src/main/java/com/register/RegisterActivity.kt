package com.register

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.base.router.BaseRouterActivity
import com.base.router.constant.ARouterPath
import com.register.databinding.ActivityRegisterBinding

@Route(path = ARouterPath.REGISTER_ACTIVITY)
class RegisterActivity : BaseRouterActivity() {
//    private val registerViewModel by viewModels<RegisterViewModel>()
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityRegisterBinding>(this, R.layout.activity_register)
        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.viewModel = registerViewModel
        binding.lifecycleOwner = this
        registerViewModel.checkVersion()
    }

}