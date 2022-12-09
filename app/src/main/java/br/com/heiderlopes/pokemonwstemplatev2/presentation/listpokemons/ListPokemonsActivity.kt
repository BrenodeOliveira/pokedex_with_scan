package br.com.heiderlopes.pokemonwstemplatev2.presentation.listpokemons

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import br.com.heiderlopes.pokemonwstemplatev2.databinding.ActivityListPokemonsBinding
import br.com.heiderlopes.pokemonwstemplatev2.domain.model.ViewState
import br.com.heiderlopes.pokemonwstemplatev2.presentation.formpokemon.FormPokemonActivity
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ListPokemonsActivity : AppCompatActivity() {

    private val listPokemonsViewModel: ListPokemonsViewModel by viewModel()

    private val picasso: Picasso by inject()

    private val viewBinding by lazy {
        ActivityListPokemonsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        listPokemonsViewModel.getPokemons()
        registerObserver()
    }

    fun registerObserver() {
        listPokemonsViewModel.pokemonResult.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    isContainerVisible(false)
                    viewBinding.rvPokemons.adapter = ListPokemonsAdapter(it.data, picasso) {
                        val intent = Intent(this, FormPokemonActivity::class.java)
                        intent.putExtra("POKEMON", it.number)
                        startActivity(intent)
                    }
                    viewBinding.rvPokemons.layoutManager = GridLayoutManager(this, 3)
                }
                is ViewState.Loading -> {
                    isContainerVisible(true)
                }
                is ViewState.Failure -> {
                    isContainerVisible(false)
                    Toast.makeText(this, it.throwable.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun isContainerVisible(isVisible: Boolean) {
        viewBinding.loading.containerLoading.isVisible = isVisible
    }
}