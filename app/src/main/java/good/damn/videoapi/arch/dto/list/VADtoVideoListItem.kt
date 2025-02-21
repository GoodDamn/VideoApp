package good.damn.videoapi.arch.dto.list

import good.damn.videoapi.arch.models.VAModelVideoList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VADtoVideoListItem(
    @SerialName("list")
    val list: List<VAModelVideoList>
)