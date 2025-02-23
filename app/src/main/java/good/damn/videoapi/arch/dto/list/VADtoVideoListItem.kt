package good.damn.videoapi.arch.dto.list

import good.damn.videoapi.arch.models.VAModelVideoListItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VADtoVideoListItem(
    val list: List<VAModelVideoListItem>
)