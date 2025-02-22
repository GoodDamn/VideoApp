package good.damn.videoapi.arch.usecases

import good.damn.videoapi.arch.models.VAModelVideoListItem
import good.damn.videoapi.arch.repos.VARepoVideo
import good.damn.videoapi.arch.state.VAStateResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.single
import javax.inject.Inject

private typealias Videos = List<VAModelVideoListItem>

class VAUseCaseVideoList @Inject constructor(
    private val repo: VARepoVideo
) {

    companion object {
        private const val TAG = "VAUseCaseVideoList"
    }

    operator fun invoke(): Flow<
        VAStateResponse<Videos>
    > = flow {
        try {
            emit(VAStateResponse.Loading())
            val videos = repo.getListVideos()
            emit(VAStateResponse.Success(videos.last()))
        } catch (e: Exception) {
            emit(VAStateResponse.Error("Error: ${e.localizedMessage}"))
        }
    }
}