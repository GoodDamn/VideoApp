package good.damn.videoapi.arch.repos

import good.damn.videoapi.arch.api.VAApiVideo
import good.damn.videoapi.arch.models.VAModelVideoDetails
import good.damn.videoapi.arch.models.VAModelVideoListItem
import good.damn.videoapi.arch.room.dao.VADaoVideo
import good.damn.videoapi.arch.state.VAStateResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VARepoVideo @Inject constructor(
    private val videoDao: VADaoVideo,
    private val api: VAApiVideo
) {

    val getListVideosDao: Flow<List<VAModelVideoListItem>>
        get() = videoDao.getVideosList()

    val getListVideos: Flow<
        VAStateResponse<List<VAModelVideoListItem>>
    > get() = flow {
        try {
            emit(VAStateResponse.Loading())
            val response = api.getListVideos()
            emit(
                VAStateResponse.Success(
                    response.list
                )
            )
        } catch (e: Exception) {
            emit(VAStateResponse.Error(
                "Error: ${e.localizedMessage}"
            ))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun add(
        list: List<VAModelVideoListItem>
    ) = withContext(
        Dispatchers.IO
    ) {
        videoDao.add(list)
    }

    fun getVideoDetailsById(
        id: Long
    ): Flow<
        VAStateResponse<
            VAModelVideoDetails
        >
    > = flow {

        try {
            emit(VAStateResponse.Loading())
            val response = api.getVideoDetailsById(id)
            emit(VAStateResponse.Success(
                response
            ))
        } catch (e: Exception) {
            emit(VAStateResponse.Error(
                "Error: ${e.localizedMessage}"
            ))
        }
    }.flowOn(Dispatchers.IO)
}