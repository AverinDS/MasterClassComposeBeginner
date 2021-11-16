package com.example.formasterclass

import com.example.formasterclass.json.RepositorySource
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://api.github.com/"
private val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
private val authInterceptor = Interceptor { chain ->
    chain.proceed(
        chain.request()
            .newBuilder()
            .addHeader("Authorization", "token ${BuildConfig.TOKEN_AUTH}")
            .addHeader("Accept", "application/vnd.github.v3+json")
            .build()
    )
}
private val client = OkHttpClient()
    .newBuilder()
    .addInterceptor(loggingInterceptor)
    .addInterceptor(authInterceptor)
    .build()

val api = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()
    .create(GitHubApi::class.java)

interface GitHubApi {

    @GET("/orgs/github/repos")
    suspend fun getRepository() : List<RepositorySource>

}
