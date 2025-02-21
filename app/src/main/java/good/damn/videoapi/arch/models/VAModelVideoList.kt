package good.damn.videoapi.arch.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VAModelVideoList(
    @SerialName("id")
    val id: Long?,

    @SerialName("title")
    val title: String?,

    @SerialName("thumbnail")
    val thumbnail: String?,

    @SerialName("subtitle")
    val subtitle: String?
)