package com.example.pokedex.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.example.pokedex.GlideApp
import com.example.pokedex.R
import com.example.pokedex.model.Pokemon


private const val ARG_POKEMON = "pokemon"

/**
 * A simple [Fragment] subclass.
 * Use the [TestFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PokemonFragment : Fragment() {
    @BindView(R.id.name)
    lateinit var name: TextView

    @BindView(R.id.weight)
    lateinit var weight: TextView

    @BindView(R.id.height)
    lateinit var height: TextView

    @BindView(R.id.sprite)
    lateinit var sprite: ImageView

    private var pokemon: Pokemon? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pokemon = it.getParcelable(ARG_POKEMON)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pokemon, container, false)
        ButterKnife.bind(this, view)
        name.text = pokemon?.name
        weight.text = getString(R.string.weight, pokemon?.weight)
        height.text = getString(R.string.height, pokemon?.height)

        GlideApp.with(this)
            .load(pokemon!!.sprites.get("front_default").toString())
            .into(sprite)

        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param pokemon The pokemon to display.
         * @return A new instance of fragment PokemonFragment.
         */
        @JvmStatic
        fun newInstance(pokemon: Pokemon?) =
            PokemonFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_POKEMON, pokemon)
                }
            }
    }
}
