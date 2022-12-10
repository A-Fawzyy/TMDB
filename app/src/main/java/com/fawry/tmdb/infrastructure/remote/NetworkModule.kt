package com.fawry.tmdb.infrastructure.remote

import com.fawry.tmdb.common.Constants
import com.fawry.tmdb.data.data_source.remote.service.MoviesService
import com.google.gson.Gson
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
class NetworkModule {

	@Singleton
	@Provides
	fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
		HttpLoggingInterceptor().apply {
			level = HttpLoggingInterceptor.Level.BODY
		}

	@Singleton
	@Provides
	fun provideGson() : Gson =
		GsonBuilder().create()

	@Singleton
	@Provides
	fun providesOkHttpClient(
		httpLoggingInterceptor: HttpLoggingInterceptor
	): OkHttpClient =
		OkHttpClient.Builder()
			.connectTimeout(60, TimeUnit.SECONDS)
			.readTimeout(60, TimeUnit.SECONDS)
			.writeTimeout(60, TimeUnit.SECONDS)
			.addNetworkInterceptor(httpLoggingInterceptor)
			.build()

	@Singleton
	@Provides
	fun providesGsonConverterFactory(gson: Gson): GsonConverterFactory =
		GsonConverterFactory.create()

	@Singleton
	@Provides
	fun provideRetrofit(
		okHttpClient: OkHttpClient,
		gsonConverterFactory: GsonConverterFactory
	): Retrofit =
		Retrofit.Builder()
			.baseUrl(Constants.BASE_URL)
			.addConverterFactory(gsonConverterFactory)
			.client(okHttpClient)
			.build()

	@Singleton
	@Provides
	fun provideMoviesService(retrofit: Retrofit): MoviesService =
		retrofit.create(MoviesService::class.java)
}
