package com.example.pokedex

import android.util.Log
import com.example.pokedex.action.DownloadPokemonListAction
import com.example.pokedex.action.PokemonListUpdatedAction
import com.example.pokedex.api.ApiClient
import com.example.pokedex.api.PokemonApi
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonListResult
import com.example.pokedex.state.AppState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.rekotlin.Action

fun appReducer(action: Action, providedState: AppState?): AppState {
    // if no state has been provided, create the default state
    var state = providedState ?: AppState()

    when (action) {
        is PokemonListUpdatedAction -> {
            state = state.copy(pokemon = action.pokemon)
        }
        is DownloadPokemonListAction -> {
            val apiService = ApiClient.client?.create(PokemonApi::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                val pokemonList = apiService?.getAll()?.results ?: emptyList()
                val pokemon = pokemonList.mapNotNull { apiService?.getPokemon(it.url) }
                withContext(Dispatchers.Main) {
                    appStore.dispatch(PokemonListUpdatedAction(pokemon))
                }
            }
        }
    }

    return state
}