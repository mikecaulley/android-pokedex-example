package com.example.pokedex.state

import com.example.pokedex.model.Pokemon
import org.rekotlin.StateType

data class AppState(
    val counter: Int = 0,
    val pokemon: List<Pokemon> = emptyList()
) : StateType