package com.common.application;

import android.app.Application;

public interface IApplicationInit {

    boolean onInitHighPriority(Application application);
    boolean onInitLowPriority(Application application);

}
