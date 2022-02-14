package com.kennenalphateam.genshin.mihoyo.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface MihoyoApi {

    @GET("game_record/genshin/api/index")
    Call<MihoyoResponse> getProfile(@Query("server") String server, @Query("role_id") String gensinUid, @Header("Cookie") String cookie);

    @GET("game_record/genshin/api/dailyNote")
    Call<MihoyoResponse> getDailyNote(@Query("server") String server, @Query("role_id") String gensinUid, @Header("Cookie") String cookie);
}
