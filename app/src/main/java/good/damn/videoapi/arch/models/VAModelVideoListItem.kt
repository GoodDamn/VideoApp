package good.damn.videoapi.arch.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "videoList")
@Serializable
data class VAModelVideoListItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String?,
    val thumbnail: String?,
    val subtitle: String?,
    val duration: Long
)