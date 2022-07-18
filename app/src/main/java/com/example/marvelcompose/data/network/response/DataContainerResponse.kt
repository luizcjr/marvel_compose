package com.example.marvelcompose.data.network.response

data class DataContainerResponse<T>(
    val offset: Int,
    val total: Int,
    val results: List<T>
)