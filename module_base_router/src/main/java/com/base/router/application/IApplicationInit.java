package com.base.router.application;

import android.app.Application;

public interface IApplicationInit {

    boolean onInitHighPriority(Application application);
    boolean onInitLowPriority(Application application);

}
