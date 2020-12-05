package br.com.house.digital.service

import br.com.house.digital.model.Comic
import br.com.house.digital.model.Comics
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Repository {

    @GET("comics")
    suspend fun getComics(
        @Query("format") format: String,
        @Query("formatType") formatType: String,
        @Query("noVariants") noVariants: Boolean,
        @Query("hasDigitalIssue") hasDigitalIssue: Boolean,
        @Query("characters") characters: Int,
        @Query("dateRange") dateRange: String,
        @Query("orderBy") orderBy: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): Comics

    @GET("comics/{id}")
    suspend fun getComic(
        @Path("id") comicId: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): Comics
}

val baseURL = "https://gateway.marvel.com/v1/public/"

val retrofit = Retrofit.Builder()
    .baseUrl(baseURL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val repository: Repository = retrofit.create(Repository::class.java)