package good.damn.videoapi.arch.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import good.damn.videoapi.arch.models.VAModelVideoListItem
import good.damn.videoapi.arch.state.VAStateResponse
import good.damn.videoapi.arch.state.VAStateVideoList
import good.damn.videoapi.arch.usecases.VAUseCaseVideoList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VAViewModelVideoList @Inject constructor(
    private val useCase: VAUseCaseVideoList
): ViewModel() {

    private val mUiState = MutableStateFlow(
        VAStateVideoList()
    )

    val uiState: StateFlow<VAStateVideoList> = mUiState

    fun getAll() = viewModelScope.launch(
        Dispatchers.IO
    ) {
        useCase().collect {
            mUiState.value = useCaseDefine(it)
        }
    }

    private inline fun useCaseDefine(
        state: VAStateResponse<List<VAModelVideoListItem>>
    ) = when (state) {
        is VAStateResponse.Error -> VAStateVideoList(
            error = state.error
        )

        is VAStateResponse.Loading -> VAStateVideoList()

        is VAStateResponse.Success -> VAStateVideoList(
            videoList = state.data
        )
    }
}