package good.damn.videoapi.arch.repos

import good.damn.videoapi.arch.api.VAApiVideo
import good.damn.videoapi.arch.dto.VADtoVideoListItem
import javax.inject.Inject

class VARepoVideoImpl @Inject constructor(
    private val api: VAApiVideo
): VARepoVideo {

    override suspend fun getVideoById(
        id: String
    ) = api.getVideoById(id)

    override suspend fun getListVideos() =
        api.getListVideos()

}