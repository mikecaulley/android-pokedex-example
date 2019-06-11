package com.example.pokedex.api

import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonListResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url
import java.net.URI

interface PokemonApi {
    @GET("pokemon")
    suspend fun getAll(): PokemonListResult

    @GET
    suspend fun getPokemon(@Url url: URI): Pokemon
}