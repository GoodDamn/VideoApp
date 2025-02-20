package good.damn.videoapi.arch.state

import good.damn.videoapi.arch.models.VAModelVideoList

data class VAStateVideoList(
    val isLoading: Boolean = false,
    val videoList: List<VAModelVideoList>? = null,
    val error: String? = null
)