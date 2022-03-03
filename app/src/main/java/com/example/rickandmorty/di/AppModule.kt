package com.example.rickandmorty.di

import com.example.rickandmorty.data.detailPageData.Episode
import com.example.rickandmorty.data.detailPageData.EpisodeList
import com.example.rickandmorty.network.RickAndMortyApiService
import com.example.rickandmorty.widget.EpisodeListDeserializer
import com.example.rickandmorty.widget.EpisodesDeserializer
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val stethoInterceptor = StethoInterceptor()

        val gson = GsonBuilder()
            .registerTypeAdapter(Episode::class.java, EpisodesDeserializer())
            .registerTypeAdapter(EpisodeList::class.java, EpisodeListDeserializer())
            .create()

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(stethoInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(RickAndMortyApiService.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideRickAndMortyApiService(retrofit: Retrofit): RickAndMortyApiService =
        retrofit.create(RickAndMortyApiService::class.java)
}