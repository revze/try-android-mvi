package id.revan.sharingsessionapp.data.network

import id.revan.sharingsessionapp.data.entities.BannerRes
import id.revan.sharingsessionapp.data.entities.NewsCategoryRes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("news_categories.php")
    suspend fun getNewsCategories(): Response<NewsCategoryRes>

    @GET("banners.php")
    suspend fun getBanners(@Query("limit") limit: Int): Response<BannerRes>
}