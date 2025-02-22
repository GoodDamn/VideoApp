package good.damn.videoapi.arch.room

import androidx.room.Database
import androidx.room.RoomDatabase
import good.damn.videoapi.arch.models.VAModelVideoListItem
import good.damn.videoapi.arch.room.dao.VADaoVideo

@Database(
    entities = [VAModelVideoListItem::class],
    version = 1,
    exportSchema = false
) abstract class VADatabaseVideo
: RoomDatabase() {
    abstract fun getVideoDao(): VADaoVideo
}