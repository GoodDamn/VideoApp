package good.damn.videoapi.arch.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VAModelVideoDetails(
    @SerialName("videoUrl")
    val videoUrl: String?,

    @SerialName("description")
    val description: String?
)