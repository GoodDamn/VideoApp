package good.damn.videoapi.arch.api

import good.damn.videoapi.arch.dto.VADtoVideoListItem
import retrofit2.http.GET
import retrofit2.http.Path

interface VAApiVideo {

    @GET("/api/v3/{id}")
    suspend fun getVideoById(
        @Path("id") id: String
    )

    @GET("/api")
    suspend fun getListVideos(): List<VADtoVideoListItem>
}