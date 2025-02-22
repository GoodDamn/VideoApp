package good.damn.videoapi.arch.repos

import good.damn.videoapi.arch.api.VAApiVideo
import good.damn.videoapi.arch.models.VAModelVideoDetails
import good.damn.videoapi.arch.models.VAModelVideoListItem
import good.damn.videoapi.arch.room.dao.VADaoVideo
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

    val getListVideosDao: Flow<
        List<VAModelVideoListItem>
    > = videoDao.getVideosList()

    suspend fun add(
        list: List<VAModelVideoListItem>
    ) = withContext(
        Dispatchers.IO
    ) {
        videoDao.add(list)
    }

    fun getListVideos(): Flow<
        List<VAModelVideoListItem>
    > = flow {
        // TODO: processing exceptions
        val response = api.getListVideos()
        emit(response.list)
    }.flowOn(Dispatchers.IO)

    fun getVideoDetailsById(
        id: Long
    ): Flow<VAModelVideoDetails> = flow {
        val response = api.getVideoDetailsById(id)
        emit(response)
    }.flowOn(Dispatchers.IO)
}