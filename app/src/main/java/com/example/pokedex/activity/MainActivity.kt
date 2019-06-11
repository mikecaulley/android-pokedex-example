package com.example.pokedex.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pokedex.R
import com.example.pokedex.fragment.PokemonFragment
import com.example.pokedex.fragment.PokemonListFragment
import com.example.pokedex.model.Pokemon

class MainActivity : AppCompatActivity(), PokemonListFragment.OnPokemonSelectedInteractionListener {
    override fun onPokemonSelectedInteraction(pokemon: Pokemon?) {
        val pokemonFragment = PokemonFragment.newInstance(pokemon)
        pushFragment(pokemonFragment)
    }

    private fun pushFragment(newFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set the starting fragment
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, PokemonListFragment.newInstance()).commit()
    }
}
