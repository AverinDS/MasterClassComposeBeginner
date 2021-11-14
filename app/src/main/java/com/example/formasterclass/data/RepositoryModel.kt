package com.example.formasterclass.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit


data class RepositoryModel(val author: String, val nameRepo: String)

val sampleData = listOf(
    RepositoryModel("Dmitry","Repository1"),
    RepositoryModel("Dmitry","Repository1"),
    RepositoryModel("Dmitry","Repository1"),
    RepositoryModel("Dmitry","Repository1"),
    RepositoryModel("Dmitry","Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1Repository1"),
    RepositoryModel("Dmitry","Repository1"),
    RepositoryModel("Dmitry","Repository1"),
    RepositoryModel("Dmitry","Repository1"),
    RepositoryModel("Dmitry","Repository1"),
    RepositoryModel("Dmitry","Repository1"),
    RepositoryModel("Dmitry","Repository1"),
    RepositoryModel("Dmitry","Repository1"),
    RepositoryModel("Dmitry","Repository1"),
    RepositoryModel("Dmitry","Repository1"),
    RepositoryModel("Dmitry","Repository1"),
    RepositoryModel("Dmitry","Repository1"),
    RepositoryModel("Dmitry","Repository1"),
)
class RepositoryListViewModel :ViewModel() {
    private val listRepository: MutableLiveData<List<RepositoryModel>> = MutableLiveData()
    private val coroutineScope = CoroutineScope(Job() + Dispatchers.IO)
    val listRepositoryData: LiveData<List<RepositoryModel>> = listRepository

    init {
        coroutineScope.launch {
            delay(3000)
            launch(Dispatchers.Main) {
                listRepository.value = sampleData
            }
        }

    }
}