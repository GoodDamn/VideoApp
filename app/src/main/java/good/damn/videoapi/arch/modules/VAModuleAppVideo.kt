package good.damn.videoapi.arch.modules

import android.content.Context
import androidx.room.PrimaryKey
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import good.damn.videoapi.arch.api.VAApiVideo
import good.damn.videoapi.arch.repos.VARepoVideo
import good.damn.videoapi.arch.room.VADatabaseVideo
import good.damn.videoapi.arch.room.dao.VADaoVideo
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VAModuleAppVideo {

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context
    ): VADatabaseVideo = Room.databaseBuilder(
        context,
        VADatabaseVideo::class.java,
        "videoDb"
    ).build()

    @Provides
    fun providesVideoDao(
        database: VADatabaseVideo
    ): VADaoVideo = database.getVideoDao()

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
        dao: VADaoVideo,
        api: VAApiVideo
    ) = VARepoVideo(
        dao,
        api
    )

}