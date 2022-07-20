package com.example.marvelcompose.data.network.response

import com.example.marvelcompose.domain.model.Character

data class CharacterResponse(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: ThumbnailResponse
)

fun CharacterResponse.toCharacterModel(): Character = Character(
    id = this.id,
    name = this.name,
    description = this.description.ifEmpty { "Esse personagem não possui descrição! :(" },
    imageUrl = "${this.thumbnail.path}.${this.thumbnail.extension}"
        .replace("http", "https")
)