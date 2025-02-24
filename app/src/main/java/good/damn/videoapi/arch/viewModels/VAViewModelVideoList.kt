package good.damn.videoapi.arch.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import good.damn.videoapi.arch.models.VAModelVideoListItem
import good.damn.videoapi.arch.repos.VARepoVideo
import good.damn.videoapi.arch.state.VAStateResponse
import good.damn.videoapi.arch.state.VAStateVideoList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VAViewModelVideoList @Inject constructor(
    private val repo: VARepoVideo
): ViewModel() {

    val getListDao: LiveData<
        List<VAModelVideoListItem>
    > = repo.getListVideosDao.flowOn(
        Dispatchers.Main
    ).asLiveData(
        viewModelScope.coroutineContext
    )

    val getList = repo
        .getListVideos
        .asLiveData()

    fun add(
        list: List<VAModelVideoListItem>
    ) = viewModelScope.launch {
        repo.add(list)
    }
}