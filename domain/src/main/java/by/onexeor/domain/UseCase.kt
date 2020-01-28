package by.onexeor.domain

import by.onexeor.domain.exception.Failure
import by.onexeor.domain.functional.Either
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Originally Created by onexeor (Savchik Viktor) on 27/01/2020
 * for TestTask (by.onexeor.domain)
 */
abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        val job = CoroutineScope(Dispatchers.IO).async { run(params) }
        CoroutineScope(Dispatchers.Main).launch {
            onResult(job.await())
        }
    }

}