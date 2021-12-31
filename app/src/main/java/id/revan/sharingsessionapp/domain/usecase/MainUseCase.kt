package id.revan.sharingsessionapp.domain.usecase

import id.revan.sharingsessionapp.external.DataState
import id.revan.sharingsessionapp.data.entities.Banner
import id.revan.sharingsessionapp.data.entities.NewsCategory
import kotlinx.coroutines.flow.Flow

interface MainUseCase {

    suspend fun getNewsCategories(): Flow<DataState<List<NewsCategory>>>
    suspend fun getBanners(limit: Int): Flow<DataState<List<Banner>>>
}