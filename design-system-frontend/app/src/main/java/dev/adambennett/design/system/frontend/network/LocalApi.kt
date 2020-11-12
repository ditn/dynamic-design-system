package dev.adambennett.design.system.frontend.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/**
 * When accessing a local server through an emulator, the URL is different. If you want to test
 * this on a real device, switch out the base URL here.
 */
private const val BASE_URL_EMULATOR = "http://10.0.2.2:8080/"
private const val BASE_URL_DEVICE = "http://0.0.0.0:8080/"

interface LocalApi {

    @GET("/theme")
    suspend fun getTheme(): ThemeModel
}

object ThemeProvider {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_EMULATOR)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val api: LocalApi = retrofit.create(LocalApi::class.java)

    suspend fun getTheme(): ThemeModel = api.getTheme()
}
