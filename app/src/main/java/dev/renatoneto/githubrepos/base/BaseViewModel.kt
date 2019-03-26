package dev.renatoneto.githubrepos.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.renatoneto.githubrepos.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.io.IOException

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext = Dispatchers.Main

    protected val jobs = ArrayList<Job>()

    val loading = MutableLiveData<Boolean>()

    val error = MutableLiveData<Int>()

    infix fun ArrayList<Job>.add(job: Job) {
        this.add(job)
    }

    fun showError(throwable: Throwable) {

        throwable.printStackTrace()

        if (throwable is IOException) {
            error.value = R.string.error_connection
            error.postValue(R.string.error_connection)
        } else {
            error.postValue(R.string.error_unexpected)
        }

    }

    override fun onCleared() {
        super.onCleared()
        jobs.forEach {

            if (!it.isCancelled) {
                it.cancel()
            }

        }
    }
}