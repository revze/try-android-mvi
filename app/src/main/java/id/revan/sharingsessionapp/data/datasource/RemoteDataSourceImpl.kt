package id.revan.sharingsessionapp.data.datasource

import id.revan.sharingsessionapp.data.entities.Banner
import id.revan.sharingsessionapp.data.entities.NewsCategory
import id.revan.sharingsessionapp.data.network.ApiService
import id.revan.sharingsessionapp.external.CoroutineDispatcherProvider
import id.revan.sharingsessionapp.external.DataState
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl(private val apiService: ApiService, private val dispatcher: CoroutineDispatcherProvider) :
    RemoteDataSource {

    override suspend fun getNewsCategories(): DataState<List<NewsCategory>> = withContext(dispatcher.io()) {
        try {
            val result = apiService.getNewsCategories()
            if (result.isSuccessful) {
                DataState.Success(result.body()?.data ?: emptyList())
            } else {
                DataState.Error(code = result.code())
            }
        } catch (e: Throwable) {
            DataState.Error(e)
        }
    }

    override suspend fun getBanners(limit: Int): DataState<List<Banner>> = withContext(dispatcher.io()) {
        try {
            val result = apiService.getBanners(limit)
            if (result.isSuccessful) {
                DataState.Success(result.body()?.data ?: emptyList())
            } else {
                DataState.Error(code = result.code())
            }
        } catch (e: Throwable) {
            DataState.Error(e)
        }
    }
}