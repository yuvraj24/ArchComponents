package com.arch.components.architecture.viewmodels

import com.arch.components.architecture.livedata.ConnectionLiveData


/**
 * Created by Yuvraj on 27/03/18.
 */
public class ConnectionViewModel : BaseViewModel() {

    private lateinit var liveData: ConnectionLiveData

    fun getConnection(): ConnectionLiveData {
        liveData = ConnectionLiveData()
        liveData.init(false)
        return liveData as ConnectionLiveData
    }

    fun registerForNetworkUpdates() {
        liveData.registerForNetworkUpdates()
    }

    fun unregisterFromNetworkUpdates() {
        liveData.unregisterFromNetworkUpdates()
    }

}
