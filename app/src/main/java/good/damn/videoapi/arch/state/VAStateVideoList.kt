package good.damn.videoapi.arch.state

import good.damn.videoapi.arch.models.VAModelVideoList

data class VAStateVideoList(
    val isLoading: Boolean = false,
    val videoList: List<VAModelVideoList> = emptyList(),
    val error: String? = null
)