package com.kennenalphateam.genshin.mihoyo.api;

import com.kennenalphateam.genshin.mihoyo.dto.CharacterRequest;
import retrofit2.Call;
import retrofit2.http.*;

public interface MihoyoApi {

    @GET("game_record/genshin/api/index")
    Call<MihoyoResponse> getProfile(@Query("server") String server, @Query("role_id") String gensinUid, @Header("Cookie") String cookie);

    @GET("game_record/genshin/api/dailyNote")
    Call<MihoyoResponse> getDailyNote(@Query("server") String server, @Query("role_id") String gensinUid, @Header("Cookie") String cookie);

    @GET("game_record/card/wapi/getGameRecordCard")
    Call<MihoyoResponse> getGameRecordCard(@Query("uid") String mihoyoId, @Header("Cookie") String cookie);

    @POST("game_record/genshin/api/character")
    Call<MihoyoResponse> getCharacterInfoList(@Body CharacterRequest request, @Header("Cookie") String cookie);
}
