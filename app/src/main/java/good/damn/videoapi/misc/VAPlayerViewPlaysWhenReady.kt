package good.damn.videoapi.misc

import androidx.media3.common.Player
import androidx.media3.ui.PlayerView

class VAPlayerViewPlaysWhenReady(
    private val player: Player
): Player.Listener {

    override fun onPlaybackStateChanged(
        playbackState: Int
    ) {
        if (playbackState == Player.STATE_READY) {
            player.play()
        }
    }

}