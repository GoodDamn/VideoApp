package good.damn.videoapi.arch.usecases

import good.damn.videoapi.arch.models.VAModelVideoList
import good.damn.videoapi.arch.repos.VARepoVideo
import good.damn.videoapi.arch.state.VAStateResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private typealias Videos = List<VAModelVideoList>

class VAUseCaseVideoList @Inject constructor(
    private val repo: VARepoVideo
) {

    operator fun invoke(): Flow<
        VAStateResponse<Videos>
    > = flow {
        try {
            emit(VAStateResponse.Loading())
            val videos = repo.getListVideos().map {
                it.toModel()
            }
            emit(VAStateResponse.Success(videos))
        } catch (e: Exception) {
            emit(VAStateResponse.Error("Error: ${e.localizedMessage}"))
        }
    }
}