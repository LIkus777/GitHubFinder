package com.app.githubfinder.presentation.repository_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.githubfinder.data.common.Resource
import com.app.githubfinder.domain.use_case.GetRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
): ViewModel() {

    private val _state = MutableStateFlow(RepositoriesState())
    val state = _state

    fun getAllRepositories(name: String) {
        getRepositoriesUseCase.invoke(name = name).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = RepositoriesState(
                        listOfRepositories = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = RepositoriesState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}