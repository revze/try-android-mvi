package id.revan.sharingsessionapp.domain.usecase

import id.revan.sharingsessionapp.data.entities.Banner
import id.revan.sharingsessionapp.data.entities.NewsCategory
import id.revan.sharingsessionapp.domain.repository.MainRepository
import id.revan.sharingsessionapp.external.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainUseCaseImpl(
    private val mainRepository: MainRepository,
) : MainUseCase {

    override suspend fun getNewsCategories(): Flow<DataState<List<NewsCategory>>> = flow {
        emit(mainRepository.getNewsCategories())
    }

    override suspend fun getBanners(limit: Int): Flow<DataState<List<Banner>>> = flow {
        emit(mainRepository.getBanners(limit))
    }
}