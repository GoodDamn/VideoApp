package good.damn.videoapi.arch.usecases

import android.util.Log
import good.damn.videoapi.arch.models.VAModelVideoList
import good.damn.videoapi.arch.repos.VARepoVideo
import good.damn.videoapi.arch.state.VAStateResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.annotation.Inherited
import javax.inject.Inject

private typealias Videos = List<VAModelVideoList>

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
            emit(VAStateResponse.Success(videos.list))
        } catch (e: Exception) {
            emit(VAStateResponse.Error("Error: ${e.localizedMessage}"))
        }
    }
}