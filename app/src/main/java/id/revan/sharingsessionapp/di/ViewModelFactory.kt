package id.revan.sharingsessionapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.revan.sharingsessionapp.domain.usecase.MainUseCase
import id.revan.sharingsessionapp.presentation.MainViewModel

class ViewModelFactory(
    private val mainUseCase: MainUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mainUseCase) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}