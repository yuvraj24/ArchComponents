package com.arch.components.api


import com.arch.components.helper.CharacterResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Yuvraj.
 */
interface NetworkService {

    @GET("/v1/public/characters")
    fun getActionCharacters(@Query("apikey") query: String,
                            @Query("ts") query1: String,
                            @Query("hash") query2: String):
            Observable<CharacterResponse>

    //    @Headers(Constants.ENCRYPT_KEY_VALUE)
    //    @POST(ApiNames.DisplayShareThought)
    //    Observable<ShareThoughtListingResponse> shareThoughtListing(@Body ShareThoughtListingRequest request);

}
