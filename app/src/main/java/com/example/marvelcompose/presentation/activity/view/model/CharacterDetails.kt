package com.example.marvelcompose.presentation.activity.view.model

import androidx.paging.PagingData
import com.example.marvelcompose.domain.model.Character
import com.example.marvelcompose.domain.model.Details
import kotlinx.coroutines.flow.Flow

data class CharacterDetails(
    val character: List<Character>,
    val comics: Flow<PagingData<Details>>,
    val series: Flow<PagingData<Details>>,
    val events: Flow<PagingData<Details>>
)
