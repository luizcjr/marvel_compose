package com.example.marvelcompose.data.network.response

import com.example.marvelcompose.domain.model.Details

data class DetailsReponse(
    val id: Int,
    val title: String,
    val thumbnail: ThumbnailResponse?
)

fun DetailsReponse.toDetailModel(): Details = Details(
    id = this.id,
    title = this.title,
    imageUrl = this.thumbnail?.let {
        "${it.path}.${it.extension}"
            .replace("http", "https")
    }
)