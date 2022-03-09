package com.carlosherrera.domain.repository

abstract class Repository<T> {
    var cache: List<T> = emptyList()
}