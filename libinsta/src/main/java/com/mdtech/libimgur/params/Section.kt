package com.mdtech.libimgur.params

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = false)
enum class Section {
    @Json(name = "hot") HOT,
    @Json(name = "top") TOP,
}