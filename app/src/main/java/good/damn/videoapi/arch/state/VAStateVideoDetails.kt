package good.damn.videoapi.arch.state

import good.damn.videoapi.arch.models.VAModelVideoDetails
import good.damn.videoapi.arch.models.VAModelVideoListItem

data class VAStateVideoDetails(
    val isLoading: Boolean = false,
    val details: VAModelVideoDetails? = null,
    val error: String? = null
)