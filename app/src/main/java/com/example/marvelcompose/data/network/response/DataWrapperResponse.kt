package com.example.marvelcompose.data.network.response

data class DataWrapperResponse<T>(
    val copyright: String,
    val data: DataContainerResponse<T>
)