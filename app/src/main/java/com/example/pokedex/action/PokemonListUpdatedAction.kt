package com.example.pokedex.action

import com.example.pokedex.model.Pokemon
import org.rekotlin.Action

data class PokemonListUpdatedAction(val pokemon: List<Pokemon>) : Action
