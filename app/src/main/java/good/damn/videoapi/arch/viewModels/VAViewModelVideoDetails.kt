package good.damn.videoapi.arch.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import good.damn.videoapi.arch.models.VAModelVideoDetails
import good.damn.videoapi.arch.models.VAModelVideoListItem
import good.damn.videoapi.arch.state.VAStateResponse
import good.damn.videoapi.arch.state.VAStateVideoDetails
import good.damn.videoapi.arch.state.VAStateVideoList
import good.damn.videoapi.arch.usecases.VAUseCaseVideoDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VAViewModelVideoDetails @Inject constructor(
    private val useCase: VAUseCaseVideoDetails
): ViewModel() {

    private val mUiState = MutableStateFlow(
        VAStateVideoDetails()
    )

    val uiState: StateFlow<VAStateVideoDetails> = mUiState

    fun getVideoDetailsById(
        id: Long
    ) = viewModelScope.launch(
        Dispatchers.IO
    ) {
        useCase(id).collect {
            mUiState.value = useCaseDefine(it)
        }
    }


    private inline fun useCaseDefine(
        state: VAStateResponse<VAModelVideoDetails>
    ) = when (state) {
        is VAStateResponse.Error -> VAStateVideoDetails(
            error = state.error
        )

        is VAStateResponse.Loading -> VAStateVideoDetails()

        is VAStateResponse.Success -> VAStateVideoDetails(
            details = state.data
        )
    }
}