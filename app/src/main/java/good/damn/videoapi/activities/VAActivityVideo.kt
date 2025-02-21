package good.damn.videoapi.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import dagger.hilt.android.AndroidEntryPoint
import good.damn.videoapi.arch.models.VAModelVideoDetails
import good.damn.videoapi.arch.state.VAStateResponse
import good.damn.videoapi.arch.state.VAStateVideoDetails
import good.damn.videoapi.arch.viewModels.VAViewModelVideoDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class VAActivityVideo
: AppCompatActivity() {

    companion object {
        const val KEY_EXTRA_ID = "id"
    }

    private val mViewModelDetails: VAViewModelVideoDetails
        by viewModels()

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        val context = this

        val id = intent.getLongExtra(
            KEY_EXTRA_ID,
            -1
        )

        if (id == -1L) {
            return
        }

        getDetailsAsync(id) {

            it.videoUrl ?: return@getDetailsAsync

            PlayerView(
                context
            ).apply {
                player = ExoPlayer.Builder(
                    context
                ).build().apply {
                    setMediaItem(
                        MediaItem.fromUri(
                            it.videoUrl
                        )
                    )
                }

                setContentView(
                    this
                )
            }
        }
    }

    private inline fun getDetailsAsync(
        id: Long,
        crossinline success: (VAModelVideoDetails) -> Unit
    ) = CoroutineScope(
        Dispatchers.IO
    ).launch {
        mViewModelDetails.getVideoDetailsById(id)
        mViewModelDetails.uiState.collectLatest {
            withContext(
                Dispatchers.Main
            ) {
                invalidateDetailsView(
                    it,
                    success
                )
            }
        }
    }

    private inline fun invalidateDetailsView(
        state: VAStateVideoDetails,
        success: (VAModelVideoDetails) -> Unit
    ) {
        if (state.isLoading) {
            return
        }

        if (state.error != null) {
            return
        }

        if (state.details == null) {
            return
        }

        success(
            state.details
        )
    }

}