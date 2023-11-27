package com.example.springbootquerydsl.common.dto

data class PageResult<T>(
    val content: Iterable<T>? = null,
    val size: Int = 0,
    val page: Int = 0,
    val pages: Int = 0,
    val hasNext: Boolean = false,
    val total: Long = 0L
)
