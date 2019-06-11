package com.example.pokedex.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.action.DownloadPokemonListAction
import com.example.pokedex.appStore
import com.example.pokedex.model.Pokemon
import com.example.pokedex.state.AppState
import org.rekotlin.StoreSubscriber

/**
 * A fragment representing a list of Pokemon.
 * Activities containing this fragment MUST implement the
 * [PokemonListFragment.OnListFragmentInteractionListener] interface.
 */
class PokemonListFragment : Fragment(), StoreSubscriber<AppState> {
    private var mListener: OnPokemonSelectedInteractionListener? = null
    private var mAdapter: PokemonRecyclerViewAdapter? = null

    override fun newState(state: AppState) {
        mAdapter?.updateData(state.pokemon)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Subscribe to state changes
        appStore.subscribe(this)
        appStore.dispatch(DownloadPokemonListAction())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPokemonSelectedInteractionListener) {
            mListener = context
            mAdapter = PokemonRecyclerViewAdapter(appStore.state.pokemon.toMutableList(), mListener)
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnPokemonSelectedInteractionListener {
        fun onPokemonSelectedInteraction(pokemon: Pokemon?)
    }

    companion object {
        @JvmStatic
        fun newInstance() = PokemonListFragment()
    }
}
