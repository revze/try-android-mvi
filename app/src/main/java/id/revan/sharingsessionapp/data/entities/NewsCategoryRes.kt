package id.revan.sharingsessionapp.data.entities

import com.squareup.moshi.Json

data class NewsCategoryRes(
    @Json(name = "data")
    val data: List<NewsCategory>? = null
)
