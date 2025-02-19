package good.damn.videoapi.arch.dto

import good.damn.videoapi.arch.models.VAModelVideoList

data class VADtoVideoListItem(
    val id: String?,
    val thumbnailUrl: String?,
    val videoUrl: String?
) {
    inline fun toModel() = VAModelVideoList(
        id, thumbnailUrl, videoUrl
    )
}