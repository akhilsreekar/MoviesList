package com.akhilsreekar.movieslist

import com.akhilsreekar.movieslist.repo.MovieRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson,okhttp:OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okhttp)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideOkHttp(interceptor: Interceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    @Provides
    fun provideInterceptor() = Interceptor {
        val url = it.request().url().newBuilder().addQueryParameter("api_key", API_KEY).build()
        val request = it.request().newBuilder().url(url).build()
        return@Interceptor it.proceed(request)
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideMovieApiService(retrofit: Retrofit): MovieApiService =
        retrofit.create(MovieApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(movieApiService: MovieApiService) = ApiHelper(movieApiService)

    @Singleton
    @Provides
    fun provideMovieRepository(apiHelper: ApiHelper) = MovieRepository(apiHelper)

}