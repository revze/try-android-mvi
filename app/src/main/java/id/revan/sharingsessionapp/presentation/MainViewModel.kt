package id.revan.sharingsessionapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.revan.sharingsessionapp.external.DataState
import id.revan.sharingsessionapp.data.entity.Banner
import id.revan.sharingsessionapp.data.entity.NewsCategory
import id.revan.sharingsessionapp.domain.usecase.MainUseCase
import id.revan.sharingsessionapp.presentation.MainIntent.FetchBanner
import id.revan.sharingsessionapp.presentation.MainIntent.FetchNewsCategory
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainUseCase: MainUseCase
) : ViewModel() {

    private val _newsCategoryState = MutableLiveData<DataState<List<NewsCategory>>>()
    val newsCategoryState: LiveData<DataState<List<NewsCategory>>>
        get() = _newsCategoryState

    private val _bannerState = MutableLiveData<DataState<List<Banner>>>()
    val bannerState: LiveData<DataState<List<Banner>>>
        get() = _bannerState

    fun setStateEvent(mainIntent: MainIntent) {
        when (mainIntent) {
            is FetchNewsCategory -> fetchNewsCategories()
            is FetchBanner -> fetchBanners(mainIntent.maxBanner)
        }
    }

    private fun fetchNewsCategories() {
        viewModelScope.launch {
            mainUseCase.getNewsCategories().onEach {
                _newsCategoryState.value = it
            }.onStart {
                _newsCategoryState.value = DataState.Loading
            }.launchIn(viewModelScope)
        }
    }

    private fun fetchBanners(maxBanner: Int) {
        viewModelScope.launch {
            mainUseCase.getBanners(maxBanner).onEach {
                _bannerState.value = it
            }.onStart {
                _bannerState.value = DataState.Loading
            }.launchIn(viewModelScope)
        }
    }
}


