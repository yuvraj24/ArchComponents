package com.arch.components.ui.activity

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.Observer
import android.arch.lifecycle.OnLifecycleEvent
import android.os.Bundle
import com.arch.components.R
import com.arch.components.architecture.livedata.ConnectionLiveData
import com.zplesac.connectionbuddy.models.ConnectivityType
import kotlinx.android.synthetic.main.activity_live_data.*
import org.jetbrains.anko.imageResource

class LiveDataActivity : BaseActivity(), LifecycleObserver {

    private lateinit var connectionLiveData: ConnectionLiveData;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        /* Here getLifecycle() is a function of AppCompatActivity class which is used to get the
           instance of LifecycleRegistry class. So we can now subscribe to it for observing to any state change
           by below line of code.
         */
        lifecycle.addObserver(this)

        initLiveData(savedInstanceState);
    }

    private fun initLiveData(savedInstanceState: Bundle?) {
        connectionLiveData = ConnectionLiveData()
        val liveData = connectionLiveData.init(savedInstanceState != null)
        liveData.observe(this, Observer() { type ->
            when (type) {
                ConnectivityType.MOBILE -> {
                    image_internet_status.imageResource = R.drawable.ic_mobile_on
                    text_internet_status.text = "Mobile " + getString(R.string.connected)
                }

                ConnectivityType.WIFI -> {
                    image_internet_status.imageResource = R.drawable.ic_wifi_on
                    text_internet_status.text = "Wifi " + getString(R.string.connected)
                }
                else -> {
                    image_internet_status.imageResource = R.drawable.ic_no_internet
                    text_internet_status.text = getString(R.string.disconnected)
                }
            }
        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun viewStart() {
        connectionLiveData.registerForNetworkUpdates()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun viewStop() {
        connectionLiveData.unregisterFromNetworkUpdates()
    }
}
