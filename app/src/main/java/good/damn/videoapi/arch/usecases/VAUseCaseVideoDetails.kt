package good.damn.videoapi.arch.usecases

import good.damn.videoapi.arch.models.VAModelVideoDetails
import good.damn.videoapi.arch.repos.VARepoVideo
import good.damn.videoapi.arch.state.VAStateResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import javax.inject.Inject

class VAUseCaseVideoDetails @Inject constructor(
     private val repo: VARepoVideo
) {
    operator fun invoke(
        id: Long
    ): Flow<
        VAStateResponse<VAModelVideoDetails>
    > = flow {
        try {
            emit(VAStateResponse.Loading())
            val details = repo.getVideoDetailsById(
                id
            )
            emit(VAStateResponse.Success(
                details.last()
            ))
        } catch (e: Exception) {
            emit(VAStateResponse.Error(
                "Error: ${e.message}"
            ))
        }
    }
}