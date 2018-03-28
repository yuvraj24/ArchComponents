package com.arch.components.architecture.viewmodels

import android.arch.lifecycle.ViewModel
import com.arch.components.architecture.repository.CharacterRepository


/**
 * Created by Yuvraj on 27/03/18.
 */
public open class BaseViewModel : ViewModel() {

    private var repository : CharacterRepository? = null;

    public fun getCharacterRepo() : CharacterRepository {
        if(repository == null){
            repository = CharacterRepository();
        }
        return repository!!;
    }
}
