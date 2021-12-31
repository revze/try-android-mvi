package id.revan.sharingsessionapp.data.repository

import id.revan.sharingsessionapp.data.datasource.RemoteDataSource
import id.revan.sharingsessionapp.data.entities.Banner
import id.revan.sharingsessionapp.data.entities.NewsCategory
import id.revan.sharingsessionapp.domain.repository.MainRepository
import id.revan.sharingsessionapp.external.DataState
import java.io.IOException

class MainRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : MainRepository {

    override suspend fun getNewsCategories(): DataState<List<NewsCategory>> {
        return remoteDataSource.getNewsCategories()
    }

    override suspend fun getBanners(limit: Int): DataState<List<Banner>> {
        return remoteDataSource.getBanners(limit)
    }
}

suspend fun <T> DataState<T>.execute(
    enableOfflineMode: Boolean = false,
    shouldSaveToLocal: Boolean = false,
    saveToLocal: () -> Unit = {},
    localData: T? = null
): DataState<T> {
    return if (this is DataState.Error) {
        if (this.throwable is IOException) {
            if (enableOfflineMode) {
                if (localData == null) {
                    this
                } else {
                    DataState.Success(localData)
                }
            } else {
                this
            }
        } else {
            this
        }
    } else {
        if (shouldSaveToLocal) {
            saveToLocal.invoke()
        }

        this
    }
}

