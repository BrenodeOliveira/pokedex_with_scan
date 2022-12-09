package br.com.heiderlopes.pokemonwstemplatev2.presentation.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.heiderlopes.pokemonwstemplatev2.R
import br.com.heiderlopes.pokemonwstemplatev2.databinding.ActivityPokedexBinding
import br.com.heiderlopes.pokemonwstemplatev2.domain.model.Pokemon
import br.com.heiderlopes.pokemonwstemplatev2.domain.model.ViewState
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class PokedexActivity : AppCompatActivity() {

    private val viewModel: PokedexViewModel by viewModel()
    private val picasso: Picasso by inject()
    private val binding: ActivityPokedexBinding by lazy {
        ActivityPokedexBinding.inflate(
            layoutInflater
        )
    }
    private val pokemonNumber by lazy { intent.getStringExtra("POKEMON") ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)

        viewModel.getPokemon(pokemonNumber)

        registerObserver()
    }

    private fun registerObserver() {
        viewModel.pokemonResult.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    setValues(it.data)
                }
                is ViewState.Loading -> {
                }
                is ViewState.Failure -> {
                    Toast.makeText(
                        this, it.throwable.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun setValues(data: Pokemon) {
        with(binding) {
            val nameData = "${data.number} ${data.name}"
            tvPokemonName.text = nameData

            picasso.load("https://pokedexdx.herokuapp.com${data.imageURL}")
                .placeholder(R.drawable.logo_pokemon)
                .into(ivPokemon)
        }
    }


}