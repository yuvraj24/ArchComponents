package com.arch.components.ui.activity

import android.arch.lifecycle.*
import android.os.Bundle
import com.arch.components.R
import com.arch.components.architecture.viewmodels.ConnectionViewModel
import com.zplesac.connectionbuddy.models.ConnectivityType
import kotlinx.android.synthetic.main.activity_live_data.*
import org.jetbrains.anko.imageResource

/**
 * Created by Yuvraj.
 */
class ViewModelActivity : BaseActivity() , LifecycleObserver {

    private lateinit var viewModel: ConnectionViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        /* Here getLifecycle() is a function of AppCompatActivity class which is used to get the
           instance of LifecycleRegistry class. So we can now subscribe to it for observing to any state change
           by below line of code.
         */
        lifecycle.addObserver(this)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ConnectionViewModel::class.java)
        viewModel.getConnection()
                .observe(this, Observer<ConnectivityType>() { type ->
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
        viewModel.registerForNetworkUpdates()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun viewStop() {
        viewModel.unregisterFromNetworkUpdates()
    }
}
