package com.daffa.moviecatalogue.core.data

import com.daffa.moviecatalogue.core.data.source.remote.network.ApiResponse
import com.daffa.moviecatalogue.core.utils.AppExecutors
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType>(private val executors: AppExecutors) {

    private var result: Flow<com.daffa.moviecatalogue.core.data.source.Resource<ResultType>> = flow {
        emit(com.daffa.moviecatalogue.core.data.source.Resource.Loading())
        val dbSource = loadFromDb().first()
        if (shouldFetch(dbSource)) {
            emit(com.daffa.moviecatalogue.core.data.source.Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDb().map { com.daffa.moviecatalogue.core.data.source.Resource.Success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDb().map { com.daffa.moviecatalogue.core.data.source.Resource.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(com.daffa.moviecatalogue.core.data.source.Resource.Error<ResultType>(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDb().map { com.daffa.moviecatalogue.core.data.source.Resource.Success(it) })
        }

    }

    protected abstract fun shouldFetch(data: ResultType?): Boolean
    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>
    protected open fun onFetchFailed() {}
    protected abstract fun loadFromDb(): Flow<ResultType>
    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<com.daffa.moviecatalogue.core.data.source.Resource<ResultType>> = result
}