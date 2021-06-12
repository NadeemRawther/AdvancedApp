package com.nads.advancedapp.roomdb

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class AdvancedViewModel (private val repository: AdvancedRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    var allWords: LiveData<List<AdvancedAppEntity>> = repository.allWords.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(advancedAppEntity: AdvancedAppEntity) = viewModelScope.launch {
        repository.insert(advancedAppEntity)
    }

}

class AdvancedViewModelFactory(private val repository: AdvancedRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdvancedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AdvancedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
