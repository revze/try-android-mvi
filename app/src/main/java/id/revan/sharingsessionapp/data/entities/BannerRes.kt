package id.revan.sharingsessionapp.data.entities

import com.squareup.moshi.Json

data class BannerRes(
    @Json(name = "data")
    val data: List<Banner>? = null
)
