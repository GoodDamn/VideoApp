package good.damn.videoapi.arch.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VAModelVideoDetails(
    val videoUrl: String?,
    val description: String?
)