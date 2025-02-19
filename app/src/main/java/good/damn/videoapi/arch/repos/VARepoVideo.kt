package good.damn.videoapi.arch.repos

import good.damn.videoapi.arch.dto.VADtoVideoListItem

interface VARepoVideo {
    suspend fun getVideoById(
        id: String
    )
    suspend fun getListVideos(): List<VADtoVideoListItem>
}