package good.damn.videoapi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import good.damn.videoapi.adapters.VAAdapterVideos
import good.damn.videoapi.arch.models.VAModelVideoList
import good.damn.videoapi.arch.state.VAStateResponse
import good.damn.videoapi.arch.state.VAStateVideoList
import good.damn.videoapi.arch.viewModels.VAViewModelVideoList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class VAFragmentListVideos
: Fragment() {

    private val mViewModelVideoList: VAViewModelVideoList by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = RecyclerView(
        requireContext()
    ).apply {
        setBackgroundColor(0)

        getListAsync {
            adapter = VAAdapterVideos(it)
        }
    }


    private inline fun getListAsync(
        crossinline success: (List<VAModelVideoList>) -> Unit
    ) = CoroutineScope(
        Dispatchers.IO
    ).launch {
        mViewModelVideoList.getAll()
        mViewModelVideoList.uiState.collectLatest {
            withContext(
                Dispatchers.Main
            ) {
                invalidateViewList(
                    it,
                    success
                )
            }
        }
    }

    private inline fun invalidateViewList(
        state: VAStateVideoList,
        success: (List<VAModelVideoList>) -> Unit
    ) {
        if (state.isLoading) {
            return
        }

        if (state.error != null) {
            return
        }

        success(
            state.videoList
        )
    }
}