package good.damn.videoapi.arch.repos

import good.damn.videoapi.arch.dto.list.VADtoVideoListItem
import good.damn.videoapi.arch.models.VAModelVideoDetails

interface VARepoVideo {
    suspend fun getListVideos(): VADtoVideoListItem

    suspend fun getVideoDetailsById(
        id: Long
    ): VAModelVideoDetails
}