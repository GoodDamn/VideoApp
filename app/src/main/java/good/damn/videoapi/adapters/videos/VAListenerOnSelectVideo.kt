package good.damn.videoapi.adapters.videos

import good.damn.videoapi.arch.models.VAModelVideoListItem

interface VAListenerOnSelectVideo {
    fun onSelectVideo(
        video: VAModelVideoListItem
    )
}