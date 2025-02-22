package good.damn.videoapi.arch.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import good.damn.videoapi.arch.models.VAModelVideoListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface VADaoVideo {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(list: List<VAModelVideoListItem>)

    @Query("select * from videoList order by id")
    fun getVideosList(): Flow<List<VAModelVideoListItem>>
}