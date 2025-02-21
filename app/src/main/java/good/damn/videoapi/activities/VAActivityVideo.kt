package good.damn.videoapi.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class VAActivityVideo
: AppCompatActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        val context = this

        PlayerView(
            context
        ).apply {

            player = ExoPlayer.Builder(
                context
            ).build().apply {
                setMediaItem(
                    MediaItem.fromUri(
                        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"
                    )
                )
            }

            setContentView(
                this
            )
        }

    }

    private inline fun getDetailsAsync(
        id: Long
    ) {

    }

}