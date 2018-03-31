package com.arch.components.architecture.livedata

import android.arch.lifecycle.MutableLiveData
import com.zplesac.connectionbuddy.ConnectionBuddy
import com.zplesac.connectionbuddy.cache.ConnectionBuddyCache
import com.zplesac.connectionbuddy.interfaces.ConnectivityChangeListener
import com.zplesac.connectionbuddy.models.ConnectivityEvent
import com.zplesac.connectionbuddy.models.ConnectivityType

/**
 * Created by Yuvraj on 15/3/17.
 */
class ConnectionLiveData : MutableLiveData<ConnectivityType>(), ConnectivityChangeListener {

    fun init(hasSavedInstanceState: Boolean): MutableLiveData<ConnectivityType> {
        if (!hasSavedInstanceState) {
            ConnectionBuddyCache.clearLastNetworkState(this)
        }
        return this
    }

    fun registerForNetworkUpdates() {
        ConnectionBuddy.getInstance().registerForConnectivityEvents(this, this)
    }

    fun unregisterFromNetworkUpdates() {
        ConnectionBuddy.getInstance().unregisterFromConnectivityEvents(this)
    }

    override fun onConnectionChange(event: ConnectivityEvent) {
        postValue(event.type);
    }
}
