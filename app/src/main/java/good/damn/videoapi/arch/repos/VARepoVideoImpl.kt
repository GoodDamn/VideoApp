package good.damn.videoapi.arch.repos

import good.damn.videoapi.arch.api.VAApiVideo
import good.damn.videoapi.arch.models.VAModelVideoDetails
import javax.inject.Inject

class VARepoVideoImpl @Inject constructor(
    private val api: VAApiVideo
): VARepoVideo {

    override suspend fun getListVideos() =
        api.getListVideos()

    override suspend fun getVideoDetailsById(
        id: Long
    ) = api.getVideoDetailsById(
        id
    )
}