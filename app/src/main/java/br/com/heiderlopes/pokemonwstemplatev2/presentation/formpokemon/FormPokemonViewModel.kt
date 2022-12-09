package br.com.heiderlopes.pokemonwstemplatev2.presentation.formpokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.heiderlopes.pokemonwstemplatev2.domain.model.Pokemon
import br.com.heiderlopes.pokemonwstemplatev2.domain.model.ViewState
import br.com.heiderlopes.pokemonwstemplatev2.domain.usecase.GetPokemonUseCase
import br.com.heiderlopes.pokemonwstemplatev2.domain.usecase.UpdatePokemonUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FormPokemonViewModel(
    val getPokemonUseCase: GetPokemonUseCase,
    val updatePokemonUseCase: UpdatePokemonUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _pokemonResult =
        MutableLiveData<ViewState<Pokemon>>()

    private val _pokemonUpdateResult =
        MutableLiveData<ViewState<Pokemon>>()

    val pokemonResult: LiveData<ViewState<Pokemon>>
        get() = _pokemonResult

    val pokemonUpdateResult: LiveData<ViewState<Pokemon>>
        get() = _pokemonUpdateResult

    fun getPokemon(number: String) {
        _pokemonResult.postValue(ViewState.Loading)
        viewModelScope.launch(dispatcher) {
            runCatching {
                getPokemonUseCase(number)
            }.onSuccess {

                _pokemonResult.postValue(successObject(it))
            }.onFailure {
                _pokemonResult.postValue(ViewState.Failure(it))
            }
        }
    }

    fun update(pokemon: Pokemon) {
        _pokemonUpdateResult.postValue(ViewState.Loading)
        viewModelScope.launch(dispatcher) {
            runCatching {
                updatePokemonUseCase(pokemon)
            }.onSuccess {

                _pokemonUpdateResult.postValue(successObject(it))
            }.onFailure {
                _pokemonUpdateResult.postValue(ViewState.Failure(it))
            }
        }
    }

    private fun successObject(it: Result<Pokemon>) =
        ViewState.Success(
            it.getOrDefault(
                Pokemon(
                    "",
                    "", "", 0, 0, 0, 0
                )
            )
        )
}