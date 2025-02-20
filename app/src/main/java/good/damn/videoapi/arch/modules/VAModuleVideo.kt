package good.damn.videoapi.arch.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import good.damn.videoapi.arch.api.VAApiVideo
import good.damn.videoapi.arch.repos.VARepoVideo
import good.damn.videoapi.arch.repos.VARepoVideoImpl
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VAModuleVideo {

    @Provides
    @Singleton
    fun providesApi(): VAApiVideo = Retrofit.Builder()
        .baseUrl(
            "https://raw.githubusercontent.com/GoodDamn/VideoDemo/main/"
        ).addConverterFactory(
            Json.asConverterFactory(
                "application/json; charset=UTF8".toMediaType()
            )
        ).build().create(
            VAApiVideo::class.java
        )

    @Provides
    @Singleton
    fun providesVideoRepo(
        api: VAApiVideo
    ): VARepoVideo = VARepoVideoImpl(
        api
    )

}