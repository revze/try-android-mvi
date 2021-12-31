package id.revan.sharingsessionapp.data.entities

import com.squareup.moshi.Json

data class NewsCategory(
    @Json(name = "category")
    val category: String? = null,

    @Json(name = "url")
    val url: String? = null
)
