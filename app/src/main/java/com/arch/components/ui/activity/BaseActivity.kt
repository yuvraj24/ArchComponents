package com.arch.components.ui.activity

import android.arch.lifecycle.LifecycleObserver
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

open class BaseActivity : AppCompatActivity(), LifecycleObserver {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
