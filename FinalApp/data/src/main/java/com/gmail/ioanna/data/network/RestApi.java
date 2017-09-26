package com.gmail.ioanna.data.network;


import com.gmail.ioanna.data.entity.Profile;
import com.gmail.ioanna.data.entity.ResponseAnswer;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestApi {

    @POST("/api/account/signup")
    Observable<ResponseAnswer> createProfile(@Body Profile profile);

    @POST("/api/account/signin")
    Observable<ResponseAnswer> saveProfile(@Body Profile profile);
}
