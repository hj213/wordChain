package chatbot_plus.service;

import java.util.List;

import chatbot_plus.model.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TelegramBotApi {
    @GET("getUpdates")
    Call<ApiResponse<List<Update>>> getUpdates(@Query("offset") long offset);

    @GET("sendMessage")
    Call<ApiResponse<Message>> sendMessage(@Query("chat_id") long chatId, @Query("text") String text);
}