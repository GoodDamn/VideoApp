package good.damn.videoapi.arch.api

import good.damn.videoapi.arch.dto.list.VADtoVideoListItem
import good.damn.videoapi.arch.models.VAModelVideoDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface VAApiVideo {

    @GET("list")
    suspend fun getListVideos(): VADtoVideoListItem

    @GET("details/{id}")
    suspend fun getVideoDetailsById(
        @Path("id") id: Long
    ): VAModelVideoDetails
}