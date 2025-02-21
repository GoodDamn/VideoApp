package good.damn.videoapi.arch.state

import good.damn.videoapi.arch.models.VAModelVideoListItem

data class VAStateVideoList(
    val isLoading: Boolean = false,
    val videoList: List<VAModelVideoListItem>? = null,
    val error: String? = null
)