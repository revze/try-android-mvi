package id.revan.sharingsessionapp.di

import id.revan.sharingsessionapp.data.datasource.RemoteDataSource
import id.revan.sharingsessionapp.data.datasource.RemoteDataSourceImpl
import id.revan.sharingsessionapp.data.network.ApiService
import id.revan.sharingsessionapp.data.network.RetrofitBuilder
import id.revan.sharingsessionapp.data.repository.MainRepositoryImpl
import id.revan.sharingsessionapp.domain.repository.MainRepository
import id.revan.sharingsessionapp.domain.usecase.MainUseCase
import id.revan.sharingsessionapp.domain.usecase.MainUseCaseImpl
import id.revan.sharingsessionapp.external.AppCoroutineDispatcherProvider
import id.revan.sharingsessionapp.external.CoroutineDispatcherProvider

object Injector {

    private fun provideApiService(): ApiService = RetrofitBuilder.apiService

    private fun provideCoroutineDispatcherProvider(): CoroutineDispatcherProvider = AppCoroutineDispatcherProvider()

    private fun provideRemoteDataSource(): RemoteDataSource =
        RemoteDataSourceImpl(provideApiService(), provideCoroutineDispatcherProvider())

    private fun provideMainRepository(): MainRepository = MainRepositoryImpl(provideRemoteDataSource())

    fun provideMainUseCase(): MainUseCase = MainUseCaseImpl(provideMainRepository())
}