package good.damn.videoapi.arch.api

import good.damn.videoapi.arch.dto.list.VADtoVideoListItem
import retrofit2.http.GET
import retrofit2.http.Path

interface VAApiVideo {

    @GET("list")
    suspend fun getListVideos(): VADtoVideoListItem

    /*@GET("/{id}")
    suspend fun getVideoById(
        @Path("id") id: String
    )*/
}