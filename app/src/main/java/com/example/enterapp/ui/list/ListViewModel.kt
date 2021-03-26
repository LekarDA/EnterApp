package com.example.enterapp

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.enterapp.data.Car
import com.example.enterapp.data.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class ListViewModel @Inject constructor(private val repository : DataRepository):
    BaseViewModel()   {

    val testDateLiveData = liveData<List<Car>>()

    init {
        getData()
    }

    private fun getData() {
        scope.launch {
            val data = repository.getNewData()
            testDateLiveData.postValue(data)
        }
    }

    fun getLastData() = testDateLiveData.value
}


abstract class BaseViewModel: ViewModel(){

    private val parentJob = SupervisorJob()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    protected val scope = CoroutineScope(coroutineContext)

    @CallSuper
    override fun onCleared() {
        scope.cancel()
    }

    fun <T> LiveData<T>.postValue(value:T){
        (this as? MutableLiveData<T>)?.postValue(value)
    }

    fun <T> ViewModel.liveData():LiveData<T> = MutableLiveData<T>()
}