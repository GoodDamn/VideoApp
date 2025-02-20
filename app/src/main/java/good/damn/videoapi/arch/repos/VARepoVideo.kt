package good.damn.videoapi.arch.repos

import good.damn.videoapi.arch.dto.list.VADtoVideoListItem

interface VARepoVideo {
    suspend fun getListVideos(): VADtoVideoListItem
}