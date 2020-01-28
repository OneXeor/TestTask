package by.onexeor.data.source.remote

import by.onexeor.domain.exception.Failure
import by.onexeor.domain.functional.Either
import by.onexeor.data.platform.NetworkHandler
import retrofit2.Call
import javax.inject.Inject

/**
 * Originally Created by onexeor (Savchik Viktor) on 27/01/2020
 * for TestTask (by.onexeor.data.source.remote)
 */

class RemoteProcessor @Inject constructor(
    private val networkHandler: NetworkHandler
) {

    // TODO need to implement more customizable errors logic
    internal fun <T, R> processRequest(
        call: Call<T>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform(response.body() ?: default))
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }
}