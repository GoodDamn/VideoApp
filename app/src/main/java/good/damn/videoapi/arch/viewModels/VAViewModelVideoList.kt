package good.damn.videoapi.arch.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import good.damn.videoapi.arch.state.VAStateVideoList
import good.damn.videoapi.arch.usecases.VAUseCaseVideoList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VAViewModelVideoList @Inject constructor(
    private val useCase: VAUseCaseVideoList
): ViewModel() {

    private val mUiState = MutableStateFlow(
        VAStateVideoList()
    )

    val uiState: StateFlow<VAStateVideoList> =
        mUiState.asStateFlow()

    fun getAll() = viewModelScope.launch(
        Dispatchers.IO
    ) {
        useCase
    }
}