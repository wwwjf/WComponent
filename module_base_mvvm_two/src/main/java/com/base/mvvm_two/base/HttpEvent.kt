package com.base.mvvm_two.base

import android.app.Activity
import android.app.ProgressDialog
import android.os.Handler
import android.view.MotionEvent
import com.base.mvvm_two.BeanLog
import com.base.mvvm_two.EventType
import com.common.eventbus.event.MessageEvent
import com.common.log.KLog
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import kotlin.collections.iterator

class HttpEvent(activity: Activity) {

    var act = activity
    var log = true
    var floatButton: FloatView
    var dialogs = HashMap<String, ProgressDialog>()


    init {
        EventBus.getDefault().register(this)
        floatButton = FloatView(activity)
    }

    /**
     * 接受展示loading event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun eventShowLoading(messageEvent: MessageEvent) {
        if (messageEvent.code != EventType.EVENT_SHOW_LOADING) {
            return
        }
        KLog.e("====event==${messageEvent.code}")
        var dialog = ProgressDialog(act)
        dialog.setMessage("加载中")
        dialog.show()
        dialogs["dialog"] = dialog

    }

    /**
     * 接受关闭loading event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun eventCloseLoading(messageEvent: MessageEvent) {
        if (messageEvent.code != EventType.EVENT_CLOSE_LOADING) {
            return
        }
        KLog.e("====event==${messageEvent.code}")
        dialogs["dialog"]?.dismiss()
        dialogs.remove("dialog")
    }


    /**
     * 接受log event
     */
//    @Subscribe(code = 31415928, threadMode = ThreadMode.MAIN)
    fun eventHttpLog(bean: BeanLog) {
        if (this.log)
            floatButton.addLog(bean)

    }

    /**
     * 注销event，关闭所有loading Dialog
     */
    fun onDestroy() {
        EventBus.getDefault().unregister(this)
        for (d in dialogs) {
            d.value.dismiss()
        }
    }

    /**
     * 开始接受log
     */
    fun onResume() {
        log = true
    }

    /**
     * 停止接受log
     */
    fun onPause() {
        log = false
    }

    /**
     * 长按Activity，显示网络调试日志
     */
    var downTime = 0L;
    var down = false;
    fun onTouchEvent(event: MotionEvent?) {
        if (event!!.action == MotionEvent.ACTION_DOWN) {
            down = true
            downTime = System.currentTimeMillis()

            Handler().postDelayed(Runnable {
                if (down && System.currentTimeMillis() - downTime > 1000) {
                    this.floatButton.show(event.x, event.y)
                }
            }, 1200)

        } else if (event!!.action == MotionEvent.ACTION_UP) {
            down = false
        }
    }
}