package id.revan.sharingsessionapp.data.entity

import com.squareup.moshi.Json

data class BannerRes(
    @Json(name = "data")
    val data: List<Banner>? = null
)
