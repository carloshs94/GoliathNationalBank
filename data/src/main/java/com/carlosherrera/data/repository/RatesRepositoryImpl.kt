package com.carlosherrera.data.repository

import arrow.core.left
import arrow.core.right
import com.carlosherrera.data.datasource.RemoteDataSourceImpl
import com.carlosherrera.domain.model.Rate
import com.carlosherrera.domain.model.Result
import com.carlosherrera.domain.repository.RatesRepository
import com.carlosherrera.domain.repository.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RatesRepositoryImpl @Inject constructor(
    private var remoteDataSourceImpl: RemoteDataSourceImpl
) : RatesRepository, Repository<Rate>() {

    override suspend fun getRates(): Result<List<Rate>> {
        if (cache.isEmpty()) {
            cache = remoteDataSourceImpl.getRates().fold({ return it.left() }) { it }
        }
        return cache.right()
    }
}