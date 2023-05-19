package ru.netology.timetojourney.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import ru.netology.timetojourney.BuildConfig
import ru.netology.timetojourney.PostBody
import ru.netology.timetojourney.dto.Flight
import ru.netology.timetojourney.dto.Flights
import java.util.concurrent.TimeUnit


const val BASE_URL = "https://vmeste.wildberries.ru/api/avia-service/twirp/aviaapijsonrpcv1.WebAviaService/"

private val logging = HttpLoggingInterceptor().apply {
    if (BuildConfig.DEBUG) {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

private val client = OkHttpClient.Builder()
    .addInterceptor(logging)
    .connectTimeout(30, TimeUnit.SECONDS)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface FlightApiService {

    @POST("GetCheap")
    @Headers("accept: application/json, text/plain", "content-type: application/json")
    fun getFlight(@Body postBody: PostBody): Call<Flights>

}

object FlightApi {
    val retrofitService: FlightApiService by lazy {
        retrofit.create()
    }
}
