package io.pjhjohn.calculator

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var ctx: Context? = null

        fun context(): Context = ctx!!
    }

    override fun onCreate() {
        super.onCreate()
        ctx = this.applicationContext
    }

}
