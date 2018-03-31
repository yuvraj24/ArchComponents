package com.arch.components.ui.activity

import android.app.Application

import com.zplesac.connectionbuddy.ConnectionBuddy
import com.zplesac.connectionbuddy.ConnectionBuddyConfiguration

/**
 * Created by Yuvraj.
 */

open class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        val networkInspectorConfiguration = ConnectionBuddyConfiguration.Builder(this).build()
        ConnectionBuddy.getInstance().init(networkInspectorConfiguration)
    }
}
