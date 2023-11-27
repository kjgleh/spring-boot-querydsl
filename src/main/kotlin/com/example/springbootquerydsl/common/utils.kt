package com.example.springbootquerydsl.common

import com.example.springbootquerydsl.common.dto.PageResult
import org.springframework.data.domain.Page

fun <T> Page<T>.toPageResult() = PageResult<T>(content, size, number, totalPages, hasNext(), totalElements)

fun <T, R> Page<T>.toPageResult(transform: (T) -> R) =
    PageResult(content.map(transform), size, number, totalPages, hasNext(), totalElements)