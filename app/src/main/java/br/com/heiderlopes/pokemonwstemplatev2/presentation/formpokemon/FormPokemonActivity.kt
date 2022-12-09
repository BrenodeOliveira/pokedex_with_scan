package br.com.heiderlopes.pokemonwstemplatev2.presentation.formpokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import br.com.heiderlopes.pokemonwstemplatev2.R
import br.com.heiderlopes.pokemonwstemplatev2.databinding.ActivityFormPokemonBinding
import br.com.heiderlopes.pokemonwstemplatev2.domain.model.Pokemon
import br.com.heiderlopes.pokemonwstemplatev2.domain.model.ViewState
import br.com.heiderlopes.pokemonwstemplatev2.presentation.utils.showToastLong
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class FormPokemonActivity : AppCompatActivity() {

    private val formPokemonViewModel: FormPokemonViewModel by viewModel()
    private val picasso: Picasso by inject()
    private lateinit var pokemon: Pokemon
    private val pokemonNumber by lazy { intent.getStringExtra("POKEMON") ?: "" }

    private val viewBinding by lazy {
        ActivityFormPokemonBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        formPokemonViewModel.getPokemon(pokemonNumber)

        registerObserver()
        setupClickListener()
    }

    private fun setupClickListener() {
        viewBinding.btSaveForm.setOnClickListener {
            pokemon.attack = viewBinding.sbAttack.progress
            pokemon.defense = viewBinding.sbDefense.progress
            pokemon.velocity = viewBinding.sbVelocity.progress
            pokemon.ps = viewBinding.sbPS.progress
            formPokemonViewModel.update(
                pokemon
            )
        }
    }

    private fun registerObserver() {
        formPokemonViewModel.pokemonResult.observe(this) {
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

        formPokemonViewModel.pokemonUpdateResult.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    showToastLong(this, "Pokémon atualizado com sucesso", 500)
                }
                is ViewState.Loading -> {
                }
                is ViewState.Failure -> {
                    showToastLong(this, it.throwable.message.toString(), 500)
                }
            }
        }

    }

    private fun setValues(pokemon: Pokemon) {
        this.pokemon = pokemon

        with(viewBinding) {
            tvPokemonNameForm.text = pokemon.name

            picasso.load("https://pokedexdx.herokuapp.com${pokemon.imageURL}")
                .placeholder(R.drawable.logo_pokemon)
                .into(ivPokemonForm)

            sbAttack.progress = pokemon.attack
            sbDefense.progress = pokemon.defense
            sbPS.progress = pokemon.ps
            sbVelocity.progress = pokemon.velocity
            tvAttackValue.text = pokemon.attack.toString()
            tvDefenseValue.text = pokemon.defense.toString()
            tvPSValue.text = pokemon.ps.toString()
            tvVelocityValue.text = pokemon.velocity.toString()
        }

        setListener(viewBinding.sbAttack, viewBinding.tvAttackValue)
        setListener(viewBinding.sbDefense, viewBinding.tvDefenseValue)
        setListener(viewBinding.sbVelocity, viewBinding.tvVelocityValue)
        setListener(viewBinding.sbPS, viewBinding.tvPSValue)
    }

    private fun setListener(seekBar: SeekBar, textView: TextView) {
        seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int, fromUser: Boolean
            ) {
                textView.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}