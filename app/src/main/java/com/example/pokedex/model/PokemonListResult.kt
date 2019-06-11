package com.example.pokedex.model

import java.net.URI

data class PokemonListResult(
    val count: Int,
    val next: URI?,
    val previous: URI?,
    val results: List<PokemonListItem>
) {
    data class PokemonListItem(val name: String, val url: URI)
}