package com.arch.components.architecture.repository

import android.arch.lifecycle.MutableLiveData
import com.arch.components.helper.CharacterResponse
import com.arch.components.utils.AppUtils
import com.arch.components.utils.Constants
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by Yuvraj on 27/03/18.
 */
class CharacterRepository : BaseRepository() {

    fun getCharacterResponse(): MutableLiveData<CharacterResponse> {

        val data = MutableLiveData<CharacterResponse>();
        getNetworkService()
                .getActionCharacters(Constants.PUBLIC_KEY,
                        "1522155136090",
                        AppUtils.getMD5String("1522155136090" +
                                Constants.PRIVATE_KEY + Constants.PUBLIC_KEY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response ->
                    AppUtils.logMe("getCharacterResponse" + " RESPONSE", Gson().toJson(response))
                    data.postValue(response);
                }, { error ->
                    error.printStackTrace()
                })
        return data
    }
}