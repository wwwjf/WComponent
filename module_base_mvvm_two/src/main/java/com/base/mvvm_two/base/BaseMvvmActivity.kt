package com.base.mvvm_two.base

import android.os.Bundle
import android.view.MotionEvent
import com.base.router.BaseRouterActivity

open class BaseMvvmActivity: BaseRouterActivity() {
    lateinit var httpEvent: HttpEvent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        httpEvent = HttpEvent(this)

    }

    override fun onResume() {
        super.onResume()
        httpEvent.onResume()
    }

    override fun onPause() {
        super.onPause()
        httpEvent.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        httpEvent.onDestroy()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        httpEvent.onTouchEvent(event)
        return super.onTouchEvent(event)
    }
}