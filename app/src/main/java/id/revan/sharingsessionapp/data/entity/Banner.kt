package id.revan.sharingsessionapp.data.entity

import com.squareup.moshi.Json

data class Banner(
    @Json(name = "title")
    val title: String? = null,

    @Json(name = "url")
    val url: String? = null
)
