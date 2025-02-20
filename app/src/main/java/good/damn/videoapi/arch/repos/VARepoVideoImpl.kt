package good.damn.videoapi.arch.repos

import good.damn.videoapi.arch.api.VAApiVideo
import javax.inject.Inject

class VARepoVideoImpl @Inject constructor(
    private val api: VAApiVideo
): VARepoVideo {

    override suspend fun getListVideos() =
        api.getListVideos()

}