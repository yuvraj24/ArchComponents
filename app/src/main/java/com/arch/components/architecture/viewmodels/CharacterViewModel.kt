package com.arch.components.architecture.viewmodels

import android.arch.lifecycle.MutableLiveData
import com.arch.components.helper.CharacterResponse


/**
 * Created by Yuvraj on 27/03/18.
 */
public class CharacterViewModel : BaseViewModel() {

    private var liveData: MutableLiveData<CharacterResponse>? = null

    fun getActionCharacters(): MutableLiveData<CharacterResponse> {
        if (liveData == null) {
            liveData = MutableLiveData<CharacterResponse>()
            liveData = getCharacterRepo().getCharacterResponse();
        }
        return liveData!!
    }

}
