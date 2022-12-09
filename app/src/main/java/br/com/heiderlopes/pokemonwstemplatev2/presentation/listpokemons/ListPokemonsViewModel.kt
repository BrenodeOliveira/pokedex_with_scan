package br.com.heiderlopes.pokemonwstemplatev2.presentation.listpokemons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.heiderlopes.pokemonwstemplatev2.domain.model.Pokemon
import br.com.heiderlopes.pokemonwstemplatev2.domain.model.ViewState
import br.com.heiderlopes.pokemonwstemplatev2.domain.usecase.GetFirstGenerationPokemonsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListPokemonsViewModel(
    val getFirstGenerationPokemonsUseCase: GetFirstGenerationPokemonsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _pokemonResult = MutableLiveData<ViewState<List<Pokemon>>>()

    val pokemonResult : LiveData<ViewState<List<Pokemon>>>
        get() = _pokemonResult

    fun getPokemons() {

        _pokemonResult.postValue(ViewState.Loading)

        viewModelScope.launch(dispatcher) {
            runCatching {
                getFirstGenerationPokemonsUseCase()
            }.onSuccess {
                _pokemonResult.postValue(ViewState.Success(it.getOrDefault(listOf())))

            }.onFailure {
                _pokemonResult.postValue(ViewState.Failure(it))
            }
        }
    }
}
