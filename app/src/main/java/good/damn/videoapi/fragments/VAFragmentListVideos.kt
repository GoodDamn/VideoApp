package good.damn.videoapi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
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

        CoroutineScope(
            Dispatchers.IO
        ).launch {

        }

        setBackgroundColor(0)
    }


    private inline fun getListAsync() = CoroutineScope(
        Dispatchers.IO
    ).launch {
        mViewModelVideoList.getAll()
        mViewModelVideoList.uiState.collectLatest {
            withContext(
                Dispatchers.Main
            ) {
                invalidateViewList(it)
            }
        }
    }

    private inline fun invalidateViewList(
        state: VAStateVideoList
    ) {
        if (state.isLoading) {
            return
        }

        if (state.error != null) {
            return
        }



    }
}