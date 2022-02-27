package com.kennenalphateam.genshin.mihoyo.api;

import com.kennenalphateam.genshin.mihoyo.dto.CharacterRequest;
import retrofit2.Call;
import retrofit2.http.*;

public interface MihoyoApi {

    @GET("game_record/genshin/api/index")
    Call<MihoyoResponse> getProfile(@Header("Cookie") String cookie, @Query("server") String server, @Query("role_id") String gensinUid);

    @GET("game_record/genshin/api/dailyNote")
    Call<MihoyoResponse> getDailyNote(@Header("Cookie") String cookie, @Query("server") String server, @Query("role_id") String gensinUid);

    @GET("game_record/card/wapi/getGameRecordCard")
    Call<MihoyoResponse> getGameRecordCard(@Header("Cookie") String cookie, @Query("uid") String mihoyoId);

    @POST("game_record/genshin/api/character")
    Call<MihoyoResponse> getCharacterInfoList(@Header("Cookie") String cookie, @Body CharacterRequest request);
}
