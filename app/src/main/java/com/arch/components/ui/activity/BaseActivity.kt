package com.arch.components.ui.activity

import android.arch.lifecycle.LifecycleObserver
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by Yuvraj.
 */
open class BaseActivity : AppCompatActivity(), LifecycleObserver {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showToast(message : String){
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show()
    }
}
