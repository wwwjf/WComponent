package com.business.hilt

import android.app.Application
import com.base.router.BaseRouterApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppHiltModule {

    //自定义的application
    @Provides
    fun provideBaseRouterApplication(application: Application): BaseRouterApplication {
        return application as BaseRouterApplication
    }
}