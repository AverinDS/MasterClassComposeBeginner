package com.example.formasterclass.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.formasterclass.api
import kotlinx.coroutines.*


data class RepositoryModel(
    val id: Int,
    val name: String,
    val description: String,
    val urlAvatar: String
)

class RepositoryListViewModel : ViewModel() {
    private val listRepository: MutableLiveData<List<RepositoryModel>> = MutableLiveData()
    private val coroutineScope = CoroutineScope(Job() + Dispatchers.IO)
    val listRepositoryData: LiveData<List<RepositoryModel>> = listRepository

    init {
        coroutineScope.launch {
            api.getRepository().map {
                RepositoryModel(
                    id = it.id,
                    name = it.name ?: "",
                    description = it.description ?: "",
                    urlAvatar = it.owner.avatar_url
                )
            }.let {
                launch(Dispatchers.Main) {
                    listRepository.value = it
                }
            }

            delay(3000)

        }

    }
}