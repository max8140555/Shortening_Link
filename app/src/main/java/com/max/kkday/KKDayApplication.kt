package com.max.kkday

import android.app.Application
import com.max.kkday.di.appCoreModule
import com.max.kkday.di.repositoryModule
import com.max.kkday.di.useCaseModule
import com.max.kkday.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KKDayApplication : Application() {

    companion object {
        /**
         * Application實體
         */
        @JvmStatic
        lateinit var instance: KKDayApplication
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        startKoin {
            androidContext(this@KKDayApplication)
            if (BuildConfig.DEBUG) {
                androidLogger(Level.NONE)
            }
            modules(
                listOf(
                    viewModelModule,
                    appCoreModule,
                    repositoryModule,
                    useCaseModule
                )
            )
        }
    }

}
