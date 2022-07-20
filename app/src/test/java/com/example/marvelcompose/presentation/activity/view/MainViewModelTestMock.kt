package com.example.marvelcompose.presentation.activity.view

import androidx.paging.PagingData
import com.example.marvelcompose.domain.model.Character
import com.example.marvelcompose.domain.model.Details

object MainViewModelTestMock {

    val characterResponseSpider = Character(1, "Spider-Man", "description", "spider-man.png")
    val characterResponseIronMan = Character(2, "Iron-Man", "description", "iron-man.png")
    private val comics = Details(1, "Comics 1", "image.png")
    private val comics2 = Details(2, "Comics 2", "image.png")
    val pagingDataComics = PagingData.from(
        listOf(
            comics,
            comics2
        )
    )

    private val series = Details(1, "Series 1", "image.png")
    private val series2 = Details(2, "Series 2", "image.png")
    val pagingDataSeries = PagingData.from(
        listOf(
            series,
            series2
        )
    )

    private val stories = Details(1, "Stories 1", "image.png")
    private val stories2 = Details(2, "Stories 2", "image.png")
    val pagingDataStories = PagingData.from(
        listOf(
            stories,
            stories2
        )
    )
}