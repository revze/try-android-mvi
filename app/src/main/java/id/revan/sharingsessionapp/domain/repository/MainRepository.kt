package id.revan.sharingsessionapp.domain.repository

import id.revan.sharingsessionapp.external.DataState
import id.revan.sharingsessionapp.data.entity.Banner
import id.revan.sharingsessionapp.data.entity.NewsCategory

interface MainRepository {

    suspend fun getNewsCategories(): DataState<List<NewsCategory>>

    suspend fun getBanners(limit: Int): DataState<List<Banner>>
}


