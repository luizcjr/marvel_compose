package com.example.marvelcompose.data.network.response

import com.example.marvelcompose.domain.model.Character

data class CharacterResponse(
    val id: Int,
    val name: String,
    val thumbnail: ThumbnailResponse
)

fun CharacterResponse.toCharacterModel(): Character = Character(
    id = this.id,
    name = this.name,
    imageUrl = "${this.thumbnail.path}.${this.thumbnail.extension}"
        .replace("http", "https")
)