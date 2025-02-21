package good.damn.videoapi.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import good.damn.videoapi.adapters.videos.VAAdapterVideos
import good.damn.videoapi.adapters.videos.VAListenerOnSelectVideo
import good.damn.videoapi.arch.models.VAModelVideoListItem
import good.damn.videoapi.arch.state.VAStateVideoList
import good.damn.videoapi.arch.viewModels.VAViewModelVideoList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class VAActivityListVideos
: AppCompatActivity(),
VAListenerOnSelectVideo {

    private val mViewModelVideoList: VAViewModelVideoList
        by viewModels()

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        val context = this

        RecyclerView(
            context
        ).apply {
            setBackgroundColor(0)

            getListAsync {
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = VAAdapterVideos(
                    it,
                    onSelectVideo = this@VAActivityListVideos
                )

                setHasFixedSize(true)
            }

            setContentView(
                this
            )
        }
    }

    override fun onSelectVideo(
        video: VAModelVideoListItem
    ) {
        startActivity(
            Intent(
                this,
                VAActivityVideo::class.java
            ).apply {
                putExtra(
                    VAActivityVideo.KEY_EXTRA_ID,
                    video.id
                )
            }
        )
    }

    private inline fun getListAsync(
        crossinline success: (List<VAModelVideoListItem>) -> Unit
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
        success: (List<VAModelVideoListItem>) -> Unit
    ) {
        if (state.isLoading) {
            return
        }

        if (state.error != null) {
            return
        }

        if (state.videoList == null) {
            return
        }

        success(
            state.videoList
        )
    }
}