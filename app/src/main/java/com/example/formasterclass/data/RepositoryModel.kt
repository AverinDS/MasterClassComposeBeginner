package com.example.formasterclass.data

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.formasterclass.api
import kotlinx.coroutines.*
import java.io.Serializable


data class RepositoryModel(
    val id: Int,
    val name: String,
    val description: String,
    val urlAvatar: String
)

val sampleData = listOf(
    RepositoryModel(0, "test", "description", "URL"),
    RepositoryModel(0, "test", "description", "URL"),
    RepositoryModel(0, "test", "description", "URL"),
    RepositoryModel(0, "test", "description", "URL"),
    RepositoryModel(0, "test", "description", "URL"),
    RepositoryModel(0, "test", "description", "URL"),
    RepositoryModel(0, "test", "description", "URL"),
    RepositoryModel(0, "test", "description", "URL"),
    RepositoryModel(0, "test", "description", "URL"),
    RepositoryModel(0, "test", "description", "URL"),
    RepositoryModel(0, "test", "description", "URL"),
    RepositoryModel(0, "test", "description", "URL")
)

data class RepositoryListState(
    val isLoading: Boolean = false,
    val isLastPageLoaded: Boolean = false,
    val listRepository: List<RepositoryModel> = emptyList(),
    var currentPage: Int = 0,
    val exception: String? = null
)

class RepositoryListViewModel : ViewModel() {
    private val state: MutableLiveData<RepositoryListState> = MutableLiveData()
    var stateLiveData: LiveData<RepositoryListState> = state
    private val coroutineScope = CoroutineScope(Job() + Dispatchers.IO)
    private val handler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        state.value = getState().copy(isLoading = false, exception = exception.message)
    }

    init {
        onNextPageLoad()
    }

    private fun getState(): RepositoryListState = state.value ?: RepositoryListState()

    private fun onBeforeNextPageLoading() {
        state.value = getState().copy(isLoading = true)
    }

    private fun onPageLoaded(listRepository: List<RepositoryModel>) {
        state.value = getState().copy(
            currentPage = getState().currentPage + 1,
            isLoading = false,
            listRepository = getState().listRepository.plus(listRepository),
            isLastPageLoaded = listRepository.isEmpty()
        )
    }

    fun onNextPageLoad() {
        onBeforeNextPageLoading()
        coroutineScope.launch(handler) {
            api.getRepository(getState().currentPage + 1).map {
                RepositoryModel(
                    id = it.id,
                    name = it.name ?: "",
                    description = it.description ?: "",
                    urlAvatar = it.owner.avatar_url
                )
            }.let {
                launch(Dispatchers.Main) {
                    onPageLoaded(it)
                }
            }
        }
    }


}