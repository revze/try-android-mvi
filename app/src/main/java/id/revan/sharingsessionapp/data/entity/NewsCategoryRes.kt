package id.revan.sharingsessionapp.data.entity

import com.squareup.moshi.Json

data class NewsCategoryRes(
    @Json(name = "data")
    val data: List<NewsCategory>? = null
)
