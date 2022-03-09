package com.carlosherrera.domain.model

import arrow.core.Either
import retrofit2.HttpException
import java.io.IOException


typealias Result<T> = Either<Error, T>

sealed class Error {
    class Server(val code: Int) : Error()
    class Unknown(val message: String) : Error()
    object Connectivity : Error()

}

fun Error.toPrintable(): String = when (this) {
    is Error.Server -> "Server error code: $code"
    is Error.Unknown -> message
    is Error.Connectivity -> "Internet Error"
}

fun Exception.toError(): Error = when (this) {
    is IOException -> Error.Connectivity
    is HttpException -> Error.Server(code())
    else -> Error.Unknown(message ?: "")
}