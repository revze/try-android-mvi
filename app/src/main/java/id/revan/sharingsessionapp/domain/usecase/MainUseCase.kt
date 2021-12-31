package id.revan.sharingsessionapp.domain.usecase

import id.revan.sharingsessionapp.external.DataState
import id.revan.sharingsessionapp.data.entity.Banner
import id.revan.sharingsessionapp.data.entity.NewsCategory
import kotlinx.coroutines.flow.Flow

interface MainUseCase {

    suspend fun getNewsCategories(): Flow<DataState<List<NewsCategory>>>
    suspend fun getBanners(limit: Int): Flow<DataState<List<Banner>>>
}